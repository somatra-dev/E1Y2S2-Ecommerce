package co.istad.matra.ecommerce.features.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository
    extends JpaRepository<Category, Integer> {

    boolean existsCategoriesByName(String name);

}
