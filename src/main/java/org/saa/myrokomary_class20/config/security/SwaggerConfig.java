package org.saa.myrokomary_class20.config.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

//@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Your API Title")
                        .description("Your API Description")
                        .version("1.0"));
    }
}
