package co.istad.matra.ecommerce.service;

import co.istad.matra.ecommerce.dto.CreateProductRequest;
import co.istad.matra.ecommerce.dto.PatchProductRequest;
import co.istad.matra.ecommerce.dto.ProductResponse;
import co.istad.matra.ecommerce.dto.UpdateProductRequest;
import org.springframework.data.domain.Page;

public interface ProductService {

    void deleteByCode(String code);

    ProductResponse patchByCode(String code, PatchProductRequest patchProductRequest);

    ProductResponse getProductByCode(String code);

    ProductResponse updateByCode(String code, UpdateProductRequest updateProductRequest);

    Page<ProductResponse> getProducts(int pageNumber, int pageSize);

    ProductResponse createNew(CreateProductRequest createProductRequest);

}
