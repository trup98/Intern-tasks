package com.springmvc.swaggerui.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api(){
        return new OpenAPI().info(
                new Info().title("Swagger Rest Api").description("Trying Swagger").version("0.0.0")).
                servers(List.of(new Server().url("/")));
    };
}
