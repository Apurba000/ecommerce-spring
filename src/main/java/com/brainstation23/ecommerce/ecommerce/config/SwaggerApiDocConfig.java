package com.brainstation23.ecommerce.ecommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerApiDocConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        final Info info = new Info().title("ECommerce App").version("1.0.0")
                .license(new License().name("© Brain Station 23 Ltd.").url("https://brainstation-23.com"));
        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .info(info);
    }
}
