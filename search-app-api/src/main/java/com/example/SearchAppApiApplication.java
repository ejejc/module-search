// base-package 명
package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.example.moduleredis", "com.example.searchappapi"})
@EnableFeignClients("com.example.clients")
public class SearchAppApiApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application-app,application-redis");
        SpringApplication.run(SearchAppApiApplication.class, args);
    }

}
