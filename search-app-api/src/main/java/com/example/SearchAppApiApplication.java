// base-package 명
package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// base-package 명이 다른 경우 bean을 찾지 못하기 때문에 별도로 컴포넌트 스캔을 하고 싶은 패키지명을 명시해줘야 한다.
@SpringBootApplication
@EnableFeignClients("com.example.clients")
public class SearchAppApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchAppApiApplication.class, args);
    }

}
