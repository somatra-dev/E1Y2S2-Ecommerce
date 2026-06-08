package co.istad.matra.ecommerce.features.order.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record OrderLineDto(

        @NotBlank(message = "Product Code is require")
        String productCode,

        @NotBlank(message = "QTY is require")
        @Positive
        Integer qty,

        @Min(0)
        @Max(100)
        Float discount
) {
}
