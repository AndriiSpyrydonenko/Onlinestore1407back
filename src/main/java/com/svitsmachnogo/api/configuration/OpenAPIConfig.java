package com.svitsmachnogo.api.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("https://svitsmachnogo.space");
        devServer.setDescription("Server URL in Stage environment");

        Contact contact = new Contact();
        contact.setEmail("educationvanyadem@gmail.com");
        contact.setName("Vanya");


        Info info = new Info()
                .title("Onlinestore1407 API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints for svitsmachnogo store.");


        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}