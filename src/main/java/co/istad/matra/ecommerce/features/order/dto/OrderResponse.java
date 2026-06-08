package co.istad.matra.ecommerce.features.order.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Builder
public record OrderResponse(

        UUID orderId,

        Instant orderedAt,

        String orderedBy,

        String remark,

        List<OrderLineDto> orderLines
) {
}
