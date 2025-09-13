package com.example.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClienteConfig {

    @Value("${chatgpt.api.url:https://api.openai.com/v1/responses}")
    private String chatGptApiUrl;

    @Bean
    public WebClient webClient(WebClient.Builder build){
        return build.baseUrl(chatGptApiUrl).build();
    }

}
