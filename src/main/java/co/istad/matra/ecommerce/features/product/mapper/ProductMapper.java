package co.istad.matra.ecommerce.features.product.mapper;

import co.istad.matra.ecommerce.features.product.Product;
import co.istad.matra.ecommerce.features.product.dto.PatchProductRequest;
import co.istad.matra.ecommerce.features.product.dto.ProductResponse;
import co.istad.matra.ecommerce.features.product.dto.UpdateProductRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.name", target = "categoryName")
    ProductResponse productToProductResponse(Product product);

    void updateProductRequestToProduct(UpdateProductRequest updateProductRequest,
                                       @MappingTarget Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchProductRequestToProduct(PatchProductRequest patchProductRequest,
                                       @MappingTarget Product product);

}
