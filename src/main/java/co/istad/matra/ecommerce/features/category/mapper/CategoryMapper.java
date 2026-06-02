package co.istad.matra.ecommerce.features.category.mapper;


import co.istad.matra.ecommerce.features.category.Category;
import co.istad.matra.ecommerce.features.category.dto.CategoryResponse;
import co.istad.matra.ecommerce.features.category.dto.CreateCategoryRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category mapCreateCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest);

    CategoryResponse mapCategoryToCategoryResponse(Category category);
}
