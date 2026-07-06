package co.istad.matra.ecommerce.features.auth;


import co.istad.matra.ecommerce.features.auth.dto.RegisterResponse;
import co.istad.matra.ecommerce.features.auth.dto.UserRegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/register")
    public RegisterResponse register(@Valid @RequestBody UserRegisterRequest userRegisterRequest){

        return null;
    }

}
