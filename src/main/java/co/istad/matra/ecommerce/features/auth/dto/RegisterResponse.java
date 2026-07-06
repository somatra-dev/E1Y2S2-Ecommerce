package co.istad.matra.ecommerce.features.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RegisterResponse(

        String userId,

        String username,

        String email,

        String firstName,

        String lastName,

        String gender,

        String biography
) {
}
