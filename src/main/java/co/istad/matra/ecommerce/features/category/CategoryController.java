package co.istad.matra.ecommerce.features.category;


import co.istad.matra.ecommerce.features.category.dto.CategoryResponse;
import co.istad.matra.ecommerce.features.category.dto.CreateCategoryRequest;
import co.istad.matra.ecommerce.features.category.dto.UpdateCategoryRequest;
import co.istad.matra.ecommerce.features.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse create(
            @Valid @RequestBody CreateCategoryRequest createCategoryRequest){
        return categoryService.create(createCategoryRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CategoryResponse> findAll(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize
    ){
        return categoryService.findAll(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public CategoryResponse findById(@PathVariable Integer id){
        return categoryService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse update(@PathVariable Integer id, @Valid @RequestBody UpdateCategoryRequest updateCategoryRequest){
        return categoryService.update(id, updateCategoryRequest);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id){
        categoryService.delete(id);
    }

}
