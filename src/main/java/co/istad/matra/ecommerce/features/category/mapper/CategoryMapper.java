package co.istad.matra.ecommerce.features.category.mapper;


import co.istad.matra.ecommerce.features.category.Category;
import co.istad.matra.ecommerce.features.category.dto.CategoryResponse;
import co.istad.matra.ecommerce.features.category.dto.CreateCategoryRequest;
import co.istad.matra.ecommerce.features.category.dto.UpdateCategoryRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category mapCreateCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest);

    CategoryResponse mapCategoryToCategoryResponse(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapUpdateCategoryRequestToCategory(UpdateCategoryRequest updateCategoryRequest, @MappingTarget Category category);
}
