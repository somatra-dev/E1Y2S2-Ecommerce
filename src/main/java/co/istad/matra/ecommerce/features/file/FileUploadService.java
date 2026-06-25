package co.istad.matra.ecommerce.features.file;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    FileResponse uploadFile(MultipartFile file);

    Resource loadFile(String fileName);
}
