package co.istad.matra.ecommerce.features.order.service;

import co.istad.matra.ecommerce.features.order.dto.CreateOrderRequest;
import co.istad.matra.ecommerce.features.order.dto.OrderResponse;
import org.springframework.security.oauth2.jwt.Jwt;

public interface OrderService {

    OrderResponse createOrder(CreateOrderRequest request);

}
