package co.istad.matra.ecommerce.features.order.controller;


import co.istad.matra.ecommerce.features.order.dto.CreateOrderRequest;
import co.istad.matra.ecommerce.features.order.dto.OrderResponse;
import co.istad.matra.ecommerce.features.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {

        return orderService.createOrder(createOrderRequest);


    }


}
