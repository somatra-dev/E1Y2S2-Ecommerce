package co.istad.matra.ecommerce.features.auth;


import co.istad.matra.ecommerce.features.auth.dto.RegisterResponse;
import co.istad.matra.ecommerce.features.auth.dto.UserRegisterRequest;
import co.istad.matra.ecommerce.features.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@Valid @RequestBody UserRegisterRequest userRegisterRequest){

        return authService.register(userRegisterRequest);
    }

}
