package co.istad.matra.ecommerce.features.auth.service.impl;

import co.istad.matra.ecommerce.features.auth.dto.RegisterResponse;
import co.istad.matra.ecommerce.features.auth.dto.UserRegisterRequest;
import co.istad.matra.ecommerce.features.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    @Override
    public RegisterResponse register(UserRegisterRequest registerRequest) {


        return null;
    }
}
