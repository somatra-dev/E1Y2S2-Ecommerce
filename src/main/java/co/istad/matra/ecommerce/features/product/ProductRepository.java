package co.istad.matra.ecommerce.features.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
    extends JpaRepository<Product, String> {

}
