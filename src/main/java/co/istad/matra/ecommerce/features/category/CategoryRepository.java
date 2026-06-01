package co.istad.matra.ecommerce.features.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository
    extends JpaRepository<Category, Integer> {
}
