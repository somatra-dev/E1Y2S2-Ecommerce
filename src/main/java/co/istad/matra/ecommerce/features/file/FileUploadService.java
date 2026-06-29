package co.istad.matra.ecommerce.features.file;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {

    FileResponse uploadFile(MultipartFile file);

    List<FileResponse> uploadMultiple(List<MultipartFile> files);

    Resource loadFile(String fileName);
}
