package com.svitsmachnogo.api.configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Value("${developer.email}")
    private String email;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .path("/swagger-ui.html", new PathItem().$ref("/swagger-ui.html"))
                .servers(server())
                .info(info());
    }

    private Info info(){
        return new Info()
                .title("Svitsmachnogo API")
                .version("Super-puper beta")
                .description("Documentation for API of Svitsmachnogo online store.")
                .contact(new Contact()
                        .email(email)
                        .name("Vanya Demydenko")
                        .url("https://projectonline-store.slack.com/archives/C05H7AC7VT4"));
    }

    private List<Server> server(){
        return List.of(new Server()
                .url("https://svitsmachnogo.space")
                .description("Default"));
    }
}
