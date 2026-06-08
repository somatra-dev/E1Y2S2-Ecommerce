package co.istad.matra.ecommerce.features.order.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record CreateOrderRequest(

        String remark,

        @NotEmpty(message = "Order must have at lease 1 item")
        List<@NotNull(message = "Item can't be null") OrderLineDto> orderLineDtoList

) {
}
