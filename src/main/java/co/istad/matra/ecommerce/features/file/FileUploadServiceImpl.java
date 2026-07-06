package co.istad.matra.ecommerce.features.file;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private final FileRepository fileRepository;
    private final FileMapper fileMapper;

    @Value("${file-upload.server-path}")
    private String serverPath;

    private String extractBaseName(String name) {
        if (name != null && name.contains(".")) {
            return name.substring(0, name.lastIndexOf("."));
        }
        return name;
    }

    @Override
    public FileResponse uploadFile(MultipartFile file) {

        return saveFile(file);
    }

    @Override
    public List<FileResponse> uploadMultiple(List<MultipartFile> files) {

        return files.stream()
                .map(this::saveFile)
                .collect(Collectors.toList());
    }

    @Override
    public Page<FileResponse> findAll(int pageNumber, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<FileUpload> fileUploads = fileRepository.findAll(pageable);
        return fileUploads.map(fileMapper::mapFromFileUploadToFileResponse);
    }

    @Override
    public FileResponse findByName(String name) {
        String baseName = extractBaseName(name);
        FileUpload fileUpload = fileRepository.findByName(baseName)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "File not found"
                ));
        return fileMapper.mapFromFileUploadToFileResponse(fileUpload);
    }

    @Override
    public void deleteByName(String name) {
        String baseName = extractBaseName(name);
        FileUpload fileUpload = fileRepository.findByName(baseName)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "File not found"
                ));

        try {
            String savedFileName = fileUpload.getName() + "." + fileUpload.getExtension();
            Path path = Paths.get(serverPath, savedFileName);
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException("Could not delete file: " + e.getMessage(), e);
        }

        fileRepository.delete(fileUpload);
    }

    @Override
    public Resource loadFile(String fileName) {
        try {
            Path filePath = Paths.get(serverPath).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("File not found or is not readable: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid file path: " + fileName, e);
        }
    }


    private FileResponse saveFile(MultipartFile file){
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !originalFilename.contains(".")) {
            throw new IllegalArgumentException("Invalid file extension");
        }

        String fileExs = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String fileName = UUID.randomUUID().toString();
        String savedFileName = fileName + "." + fileExs;

        // Create path to store image
        Path path = Paths.get(serverPath, savedFileName);

        try {
            Files.createDirectories(path.getParent());
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            throw new RuntimeException("Could not save file: " + e.getMessage(), e);
        }

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file/")
                .path(savedFileName)
                .toUriString();

        FileUpload fileUpload = new FileUpload();
        fileUpload.setName(fileName);
        fileUpload.setExtension(fileExs);
        fileUpload.setMediaType(file.getContentType());
        fileUpload.setSize(file.getSize());

        // save to db
        fileRepository.save(fileUpload);

        return FileResponse.builder()
                .name(savedFileName)
                .size(file.getSize())
                .mediaType(file.getContentType())
                .uri(uri)
                .build();
    }
}
