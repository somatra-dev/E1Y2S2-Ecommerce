package co.istad.matra.ecommerce.features.category.service.impl;

import co.istad.matra.ecommerce.features.category.Category;
import co.istad.matra.ecommerce.features.category.CategoryRepository;
import co.istad.matra.ecommerce.features.category.dto.CategoryResponse;
import co.istad.matra.ecommerce.features.category.dto.CreateCategoryRequest;
import co.istad.matra.ecommerce.features.category.dto.UpdateCategoryRequest;
import co.istad.matra.ecommerce.features.category.mapper.CategoryMapper;
import co.istad.matra.ecommerce.features.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ApiVersionMethodArgumentResolver;

import java.util.List;

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

        Pageable pageable = PageRequest.of(pageNumber, pageSize,
                Sort.Direction.DESC, "id");
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(categoryMapper::mapCategoryToCategoryResponse);
    }

    @Override
    public CategoryResponse findById(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category with id %d not found", categoryId)));

        return categoryMapper.mapCategoryToCategoryResponse(category);
    }

    @Override
    public void delete(Integer categoryId) {

        if (!categoryRepository.existsById(categoryId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category with id %d not found", categoryId));
        }
        categoryRepository.deleteById(categoryId);

    }

    @Override
    public CategoryResponse update(Integer categoryId, UpdateCategoryRequest updateCategoryRequest) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category with id %d not found", categoryId)));

        if (updateCategoryRequest.name() != null && !updateCategoryRequest.name().trim().isEmpty()) {
            if (categoryRepository.existsCategoriesByName(updateCategoryRequest.name()) &&
                    !category.getName().equalsIgnoreCase(updateCategoryRequest.name())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Category name already exists");
            }
        }

        categoryMapper.mapUpdateCategoryRequestToCategory(updateCategoryRequest, category);

        return categoryMapper.mapCategoryToCategoryResponse(categoryRepository.save(category));
    }
}
