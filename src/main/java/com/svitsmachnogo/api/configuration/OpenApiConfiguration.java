package com.svitsmachnogo.api.configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.SpecVersion;
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
        Server server = new Server();
        PathItem item = new PathItem();
        Info info = createInfo();
        item.$ref("/swagger-ui.html");
        server.setUrl("https://svitsmachnogo.space");
        return new OpenAPI()
                .servers(List.of(server))
                .path("/swagger-ui.html", item)
                .info(info);
    }

    private Info createInfo(){
        return new Info()
                .title("Title")
                .version("Super-puper beta")
                .summary("Svitsmachnogo API")
                .description("Documentation for API of Svitsmachnogo online store.")
                .contact(new Contact().email(email));
    }
}
