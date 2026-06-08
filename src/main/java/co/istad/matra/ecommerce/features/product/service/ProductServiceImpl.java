package co.istad.matra.ecommerce.features.product.service;

import co.istad.matra.ecommerce.exception.ResourceNotFoundException;
import co.istad.matra.ecommerce.features.category.Category;
import co.istad.matra.ecommerce.features.product.Product;
import co.istad.matra.ecommerce.features.product.dto.CreateProductRequest;
import co.istad.matra.ecommerce.features.product.dto.PatchProductRequest;
import co.istad.matra.ecommerce.features.product.dto.ProductResponse;
import co.istad.matra.ecommerce.features.product.dto.UpdateProductRequest;
import co.istad.matra.ecommerce.features.product.mapper.ProductMapper;
import co.istad.matra.ecommerce.features.category.CategoryRepository;
import co.istad.matra.ecommerce.features.product.ProductRepository;
import co.istad.matra.ecommerce.util.GenerateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public void deleteByCode(String code) {
        Product product = productRepository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Product code not found"));
        productRepository.delete(product);
    }

    @Override
    public ProductResponse patchByCode(String code, PatchProductRequest patchProductRequest) {
        Product product = productRepository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Product code not found"));

        if (patchProductRequest.categoryId() != null) {
            Category category = categoryRepository
                    .findById(patchProductRequest.categoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category ID not found"));
            product.setCategory(category);
        }

        productMapper.patchProductRequestToProduct(
                patchProductRequest,
                product
        );

        product = productRepository.save(product);

        return productMapper.productToProductResponse(product);
    }

    @Override
    public ProductResponse getProductByCode(String code) {
        return productRepository.findById(code)
                .map(productMapper::productToProductResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Product code not found"));
    }

    @Override
    public ProductResponse updateByCode(String code, UpdateProductRequest updateProductRequest) {
        Product product = productRepository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Product code not found"));

        productMapper.updateProductRequestToProduct(updateProductRequest, product);

        product = productRepository.save(product);

        return productMapper.productToProductResponse(product);
    }

    @Override
    public Page<ProductResponse> getProducts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return productRepository
                .findAll(pageable)
                .map(productMapper::productToProductResponse);
    }

    @Override
    public ProductResponse createNew(CreateProductRequest createProductRequest) {
        Category category = categoryRepository
                .findById(createProductRequest.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category ID not found"));

        Product product = productMapper.createProductRequestToProduct(createProductRequest);
        product.setCategory(category);
        product.setCode(GenerateUtil.randomProductCode());
        product.setIsAvailable(true);

        product = productRepository.save(product);

        return productMapper.productToProductResponse(product);
    }
}
