package co.istad.matra.ecommerce.security.keycloak;

import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class KeycloakAdminConfig {

    private final KeycloakProperties keycloakProperties;

    @Bean
    public Keycloak keycloak() {

        IO.println("Keycloak properties: " + keycloakProperties);

        return KeycloakBuilder.builder()
                .clientId(keycloakProperties.getClientId())
                .clientSecret(keycloakProperties.getClientSecret())
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .realm(keycloakProperties.getRealm())
                .serverUrl(keycloakProperties.getServerUrl())
                .build();
    }


}
