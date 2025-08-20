package team.mut4.trip.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class SwaggerConfig {

    @Value("${swagger.server-url}")
    private String serverUrl;

    @Bean
    public OpenAPI openAPI() {
        Map<String, String> profileServerUrlMap = Map.of(
                "local", "http://localhost:8080",
                "dev", serverUrl
        );
        return new OpenAPI()
                .info(new Info()
                        .title("Mut4-Backend API")
                        .description("Mut4 API 입니다.")
                )
                .servers(profileServerUrlMap.entrySet().stream()
                        .map(entry -> new Server().url(entry.getValue()).description("Mut4 API " + entry.getKey().toUpperCase()))
                        .collect(Collectors.toList())
                );
    }

}


