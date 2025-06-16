package br.com.estacionamento.Estacionamento.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Estacionamento")
                        .version("1.0")
                        .description("Projeto acadêmico com controle de entrada e saída de veículos.")
                        .contact(new Contact()
                                .name("João Francisco")
                                .email("joao@email.com")
                        )
                );
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("estacionamento")
                .pathsToMatch("/api/**")
                .build();
    }
}
