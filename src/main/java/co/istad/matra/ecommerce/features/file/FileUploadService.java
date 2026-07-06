package co.istad.matra.ecommerce.features.file;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {

    FileResponse uploadFile(MultipartFile file);

    List<FileResponse> uploadMultiple(List<MultipartFile> files);

    Page<FileResponse> findAll(int pageNumber, int pageSize);

    FileResponse findByName(String name);

    void deleteByName(String name);

    Resource loadFile(String fileName);
}
