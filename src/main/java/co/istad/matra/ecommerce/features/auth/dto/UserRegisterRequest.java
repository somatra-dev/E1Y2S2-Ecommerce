package co.istad.matra.ecommerce.features.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UserRegisterRequest(

        @NotBlank(message = "Username is require...!")
        @Size(min = 3, max = 255)
        String username,

        @NotBlank(message = "Password is require...!")
        @Size(max = 20)
        String password,

        @NotBlank(message = "Confirm Password is require...!")
        @Size(max = 20)
        String confirmPassword,

        @NotBlank(message = "Email is require...!")
        @Email
        String email,

        @NotBlank(message = "First Name is require...!")
        @Size(max = 255)
        String firstName,

        @NotBlank(message = "First Name is require...!")
        @Size(max = 255)
        String lastName,

        String gender,

        String biography

) {
}
