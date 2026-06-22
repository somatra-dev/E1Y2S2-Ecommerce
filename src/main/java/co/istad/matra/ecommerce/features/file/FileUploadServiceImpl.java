package co.istad.matra.ecommerce.features.file;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${file-upload.server-path}")
    private String serverPath;

    @Override
    public FileResponse uploadFile(MultipartFile file) {

        String fileName = UUID.randomUUID().toString();

        String fileExs = Objects
                .requireNonNull(file.getOriginalFilename())
                .substring(file.getOriginalFilename().lastIndexOf("."), 1);

        // Create path to store image
        Path path = Paths.get(String.format("%s%s.%s", serverPath, fileName, fileExs));

        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return FileResponse.builder()


                .build();
    }
}
