package co.istad.matra.ecommerce.security.keycloak;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "keycloak")
@Setter
@Getter
@ToString
public class KeycloakProperties {

    private String ServerUrl;

    private String ClientId;

    private String ClientSecret;

    private String Realm;

    private String GrantType;
}
