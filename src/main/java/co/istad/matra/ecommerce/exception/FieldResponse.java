package co.istad.matra.ecommerce.exception;

public record FieldResponse(
        String field,
        String reason
) {
}
