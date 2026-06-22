package co.istad.matra.ecommerce.features.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    FileResponse uploadFile(MultipartFile file);
}
