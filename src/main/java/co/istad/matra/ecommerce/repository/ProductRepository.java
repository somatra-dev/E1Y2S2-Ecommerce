package co.istad.matra.ecommerce.repository;

import co.istad.matra.ecommerce.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
    extends JpaRepository<Product, String> {

}
