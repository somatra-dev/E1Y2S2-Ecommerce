package co.istad.matra.ecommerce.features.auth.service.impl;

import co.istad.matra.ecommerce.features.auth.dto.RegisterResponse;
import co.istad.matra.ecommerce.features.auth.dto.UserRegisterRequest;
import co.istad.matra.ecommerce.features.auth.service.AuthService;
import co.istad.matra.ecommerce.security.keycloak.KeycloakProperties;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final Keycloak keycloak;
    private final KeycloakProperties keycloakProperties;

    @Override
    public RegisterResponse register(UserRegisterRequest registerRequest) {

        // validate password
        if (!registerRequest.password().equals(registerRequest.confirmPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password does not matching...!");
        }

        // Create Keycloak UserRepresentation
        UserRepresentation user = new UserRepresentation();
        user.setUsername(registerRequest.username());
        user.setEmail(registerRequest.email());
        user.setFirstName(registerRequest.firstName());
        user.setLastName(registerRequest.lastName());
        user.setEnabled(true);
        user.setEmailVerified(false);

        // prepare for custom attribute
        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("gender", List.of(registerRequest.gender()));
        attributes.put("biography", List.of(registerRequest.biography()));
        // set custom attribute
        user.setAttributes(attributes);

        // prepare for set credential
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(registerRequest.password());
        user.setCredentials(List.of(credentialRepresentation));

        UsersResource usersResource = keycloak
                .realm(keycloakProperties.getRealm())
                .users();

        // save user to Keycloak
        try (Response response = usersResource.create(user)) {

            log.info("Get response status code {}", response.getStatus());

            if (response.getStatus() == HttpStatus.CREATED.value()) {
                UserRepresentation userRepresentation = usersResource.search(user.getUsername())
                        .getFirst();
                log.info("User ID: {}", userRepresentation.getId());

                return RegisterResponse.builder()
                        .userId(userRepresentation.getId())
                        .username(registerRequest.username())
                        .email(registerRequest.email())
                        .firstName(registerRequest.firstName())
                        .lastName(registerRequest.lastName())
                        .gender(registerRequest.gender())
                        .biography(registerRequest.biography())
                        .build();

            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
