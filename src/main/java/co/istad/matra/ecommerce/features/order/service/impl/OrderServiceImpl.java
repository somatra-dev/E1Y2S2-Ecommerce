package co.istad.matra.ecommerce.features.order.service.impl;

import co.istad.matra.ecommerce.exception.ResourceNotFoundException;
import co.istad.matra.ecommerce.features.order.Order;
import co.istad.matra.ecommerce.features.order.OrderLine;
import co.istad.matra.ecommerce.features.order.OrderRepository;
import co.istad.matra.ecommerce.features.order.dto.CreateOrderRequest;
import co.istad.matra.ecommerce.features.order.dto.OrderResponse;
import co.istad.matra.ecommerce.features.order.mapper.OrderMapper;
import co.istad.matra.ecommerce.features.order.service.OrderService;
import co.istad.matra.ecommerce.features.product.Product;
import co.istad.matra.ecommerce.features.product.ProductRepository;
import co.istad.matra.ecommerce.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;

    @Override
    public OrderResponse createOrder(CreateOrderRequest request) {


        List<OrderLine> validOrderLine = new ArrayList<>();

        // step 1: validate the product code
        boolean match = request.orderLineDtoList().stream()
                .allMatch(orderLineDto -> {

                    boolean exists = productRepository.existsById(orderLineDto.productCode());
                    if (exists){
                        Product validProduct = productRepository.findById(orderLineDto.productCode())
                                .orElseThrow(() -> new ResourceNotFoundException("product not found"));

                        OrderLine orderLine = new OrderLine();
                        orderLine.setProduct(validProduct);
                        orderLine.setQty(orderLineDto.qty());
                        orderLine.setDiscount(orderLineDto.discount());
                        validOrderLine.add(orderLine);
                    }
                    return exists;
                });

        if(!match) {
            throw new ResourceNotFoundException("Product not found");
        }

        Order order = orderMapper.mapOrderRequestToOrder(request);
        order.setOrderedAt(Instant.now());
        order.setIsDeleted(false);
        order.setOrderedBy(AuthUtil.extractUserId());

        order.setOrderLines(validOrderLine);

        return orderMapper.mapOrderToOrderResponse(orderRepository.save(order));
    }
}
