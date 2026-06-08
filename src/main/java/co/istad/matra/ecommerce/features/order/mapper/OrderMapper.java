package co.istad.matra.ecommerce.features.order.mapper;


import co.istad.matra.ecommerce.features.order.Order;
import co.istad.matra.ecommerce.features.order.OrderLine;
import co.istad.matra.ecommerce.features.order.dto.CreateOrderRequest;
import co.istad.matra.ecommerce.features.order.dto.OrderLineDto;
import co.istad.matra.ecommerce.features.order.dto.OrderResponse;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order mapOrderRequestToOrder(CreateOrderRequest createOrderRequest);

    OrderResponse mapOrderToOrderResponse(Order order);

    default List<OrderLineDto> mapOrderLineToOrderLineDto(@NonNull List<OrderLine> orderLines) {
        return orderLines.stream()
                .map(orderLine -> OrderLineDto.builder()
                        .productCode(orderLine.getProduct().getCode())
                        .qty(orderLine.getQty())
                        .discount(orderLine.getDiscount())
                        .build())
                .toList();
    }
}
