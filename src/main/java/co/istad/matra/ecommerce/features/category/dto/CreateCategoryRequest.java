package co.istad.matra.ecommerce.features.category.dto;


import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CreateCategoryRequest(

        @Size(min = 3, max = 100)
        String name,
        String description,
        String categoryIcon

) {
}
