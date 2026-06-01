package co.istad.matra.ecommerce.features.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderLineRepository extends
        JpaRepository<OrderLine, UUID> {
}
