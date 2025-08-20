package team.mut4.trip.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Configuration
public class WebClientConfig {

//    @Value("${fastapi.url}")
//    private String FastApiUrl;

//    @Bean
//    public WebClient webClient() {
//        return WebClient.builder()
//                .baseUrl(FastApiUrl)
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .build();
//    }
}