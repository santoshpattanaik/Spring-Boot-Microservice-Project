package com.sbm.moviecatalogservice.configuration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
class BeanConfiguration {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder getWebClientBuilder(){
        return WebClient.builder();
    }
}
