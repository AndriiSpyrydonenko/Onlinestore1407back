package com.svitsmachnogo.api.configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        PathItem item = new PathItem();
        item.$ref("/swagger-ui.html");

        server.setUrl("https://svitsmachnogo.space");
        return new OpenAPI().servers(List.of(server)).path("/swagger-ui.html", item);
    }
}
