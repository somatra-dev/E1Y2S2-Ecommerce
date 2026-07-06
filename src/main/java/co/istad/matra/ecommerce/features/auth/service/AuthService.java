package co.istad.matra.ecommerce.features.auth.service;

import co.istad.matra.ecommerce.features.auth.dto.RegisterResponse;
import co.istad.matra.ecommerce.features.auth.dto.UserRegisterRequest;

public interface AuthService {

    RegisterResponse register(UserRegisterRequest registerRequest);
}
