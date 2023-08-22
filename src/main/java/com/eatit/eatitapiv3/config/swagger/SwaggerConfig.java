package com.eatit.eatitapiv3.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI configureOpenApi() {
        return new OpenAPI().info(
                new Info()
                        .title("Eat-It API Server")
                        .description("Eat-it API Server")
        );
    }
}
