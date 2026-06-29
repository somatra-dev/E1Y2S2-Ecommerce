package co.istad.matra.ecommerce.features.file;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private final FileRepository fileRepository;

    @Value("${file-upload.server-path}")
    private String serverPath;

    @Override
    public FileResponse uploadFile(MultipartFile file) {
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
}
