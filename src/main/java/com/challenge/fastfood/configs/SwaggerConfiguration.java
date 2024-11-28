package com.challenge.fastfood.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {

	@Bean
	OpenAPI springShopOpenAPI() {
		return new OpenAPI().info(new Info().title("Fastfood Payment Service").description("Fastfood Payment Service application."));
	}

}
