package co.istad.matra.ecommerce.repository;

import co.istad.matra.ecommerce.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends
        JpaRepository<Order, UUID> {
}
