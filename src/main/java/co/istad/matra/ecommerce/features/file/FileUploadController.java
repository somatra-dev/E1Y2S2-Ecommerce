package co.istad.matra.ecommerce.features.file;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FileResponse upload(@RequestPart MultipartFile file){
        return fileUploadService.uploadFile(file);
    }

    @PostMapping("/multiple")
    @ResponseStatus(HttpStatus.CREATED)
    public List<FileResponse> uploadMultiple(@RequestPart List<MultipartFile> files){
        return fileUploadService.uploadMultiple(files);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable String fileName) {
        Resource resource = fileUploadService.loadFile(fileName);
        MediaType mediaType = MediaTypeFactory.getMediaType(resource)
                .orElse(MediaType.APPLICATION_OCTET_STREAM);
        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping
    public Page<FileResponse> findAll(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return fileUploadService.findAll(pageNumber, pageSize);
    }

    @GetMapping("/{name}/info")
    public FileResponse findByName(@PathVariable String name) {
        return fileUploadService.findByName(name);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByName(@PathVariable String name) {
        fileUploadService.deleteByName(name);
    }
}
