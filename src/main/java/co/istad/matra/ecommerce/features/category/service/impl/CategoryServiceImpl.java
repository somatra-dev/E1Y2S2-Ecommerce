package co.istad.matra.ecommerce.features.category.service.impl;

import co.istad.matra.ecommerce.features.category.Category;
import co.istad.matra.ecommerce.features.category.CategoryRepository;
import co.istad.matra.ecommerce.features.category.dto.CategoryResponse;
import co.istad.matra.ecommerce.features.category.dto.CreateCategoryRequest;
import co.istad.matra.ecommerce.features.category.mapper.CategoryMapper;
import co.istad.matra.ecommerce.features.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ApiVersionMethodArgumentResolver;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public CategoryResponse create(CreateCategoryRequest createCategoryRequest) {

        if(categoryRepository.existsCategoriesByName(createCategoryRequest.name())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category Name already exist"
            );
        }
        log.info("Category inserting...!");
        Category category = categoryMapper
                .mapCreateCategoryRequestToCategory(createCategoryRequest);
        category = categoryRepository.save(category);

        return categoryMapper.mapCategoryToCategoryResponse(category);
    }

    @Override
    public Page<CategoryResponse> findAll(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Sort sort = Sort.by(Sort.DEFAULT_DIRECTION, "id");
        return null;
    }
}
