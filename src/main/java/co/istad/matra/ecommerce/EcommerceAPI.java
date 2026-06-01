package co.istad.matra.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

// Structure project
// 1. Structure by layer
// 2. Structure by features
@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class EcommerceAPI {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceAPI.class, args);
	}

}
