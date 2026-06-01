package co.istad.matra.ecommerce.repository;

import co.istad.matra.ecommerce.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository
    extends JpaRepository<Category, Integer> {
}
