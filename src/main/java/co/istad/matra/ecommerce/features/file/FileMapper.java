package co.istad.matra.ecommerce.features.file;


import org.jspecify.annotations.NonNull;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Value;

@Mapper(componentModel = "spring")
public abstract class FileMapper {

    @Value("${file-upload.base-uri}")
    private String baseUri;

    FileResponse mapFromFileUploadToFileResponse(@NonNull FileUpload fileUpload){
        String fileNameWithExtension = fileUpload.getName() + "." + fileUpload.getExtension();
        return FileResponse.builder()
                .name(fileNameWithExtension)
                .mediaType(fileUpload.getMediaType())
                .uri(baseUri + "/" + fileNameWithExtension)
                .size(fileUpload.getSize())
                .build();
    }

}
