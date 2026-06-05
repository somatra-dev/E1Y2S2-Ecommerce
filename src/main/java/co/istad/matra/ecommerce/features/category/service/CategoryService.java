package co.istad.matra.ecommerce.features.category.service;

import co.istad.matra.ecommerce.features.category.dto.CategoryResponse;
import co.istad.matra.ecommerce.features.category.dto.CreateCategoryRequest;
import co.istad.matra.ecommerce.features.category.dto.UpdateCategoryRequest;
import org.springframework.data.domain.Page;

public interface CategoryService {

    CategoryResponse create(CreateCategoryRequest createCategoryRequest);

    Page<CategoryResponse> findAll(int pageNumber, int pageSize);

    CategoryResponse findById(Integer categoryId);

    void delete(Integer categoryId);

    CategoryResponse update(Integer categoryId, UpdateCategoryRequest updateCategoryRequest);
}
