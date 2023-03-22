package com.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestConfiguration {
    @Bean
    public void contextLoads() {
        System.setProperty("spring.config.name", "application-app,application-redis");
    }
}
