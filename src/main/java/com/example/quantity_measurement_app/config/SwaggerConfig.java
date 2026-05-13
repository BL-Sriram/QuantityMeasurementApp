package com.example.quantity_measurement_app.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        // Name of security scheme used in Swagger
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()

                // Basic API information displayed in Swagger UI
                .info(new Info()
                        .title("Quantity Measurement API")
                        .version("1.0")
                        .description("JWT Authentication APIs"))

                /*
                 * Adds JWT authentication globally
                 * so secured APIs can use Authorize button
                 */
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName)
                )

                // Configure JWT Bearer Authentication
                .components(
                        new Components().addSecuritySchemes(securitySchemeName,

                                        new SecurityScheme()

                                                // Name visible in Swagger
                                                .name(securitySchemeName)

                                                // Authentication type = HTTP
                                                .type(SecurityScheme.Type.HTTP)

                                                // Bearer token authentication
                                                .scheme("bearer")

                                                // Token format
                                                .bearerFormat("JWT")
                                )
                );
    }
}