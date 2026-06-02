package co.istad.matra.ecommerce.features.category.service;

import co.istad.matra.ecommerce.features.category.dto.CategoryResponse;
import co.istad.matra.ecommerce.features.category.dto.CreateCategoryRequest;
import org.hibernate.query.Page;

public interface CategoryService {

    CategoryResponse create(CreateCategoryRequest createCategoryRequest);

    Page<CategoryResponse> findAll(int pageNumber, int pageSize);
}
