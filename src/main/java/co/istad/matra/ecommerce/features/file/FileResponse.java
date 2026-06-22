package co.istad.matra.ecommerce.features.file;


import lombok.Builder;

@Builder
public record FileResponse(

        String name,

        Long size,

        String mediaType,

        String uri
) {
}
