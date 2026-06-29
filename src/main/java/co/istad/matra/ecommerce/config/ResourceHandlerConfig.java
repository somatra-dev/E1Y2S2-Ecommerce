package co.istad.matra.ecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class ResourceHandlerConfig implements WebMvcConfigurer {

    @Value("${file-upload.server-path}")
    String serverPath;

    @Value("${file-upload.client-path}")
    String clientPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler(clientPath + "/**")
                .addResourceLocations("file:" + serverPath);

    }
}
