package dev.ococa.api;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi moodApi() {
        return GroupedOpenApi.builder()
            .group("mood操作")
            .pathsToMatch("/api/mood/**")
            .build();
    }
    @Bean
    public GroupedOpenApi unsplashApi() {
        return GroupedOpenApi.builder()
            .group("unsplashへのプロキシ")
            .pathsToMatch("/api/Unsplash/**")
            .build();
    }
}