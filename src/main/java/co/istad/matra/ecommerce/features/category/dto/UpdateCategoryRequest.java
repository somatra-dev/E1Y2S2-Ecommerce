package co.istad.matra.ecommerce.features.category.dto;


import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UpdateCategoryRequest(

        @Size(min = 3, max = 50)
        String name,
        String description,
        String categoryIcon

) {
}
