package com.example.clients.feign.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class KakaoFeignConfig {

    @Bean
    public KaKaoFeignConfiguration errorDecoder() {
        return new KaKaoFeignConfiguration();
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("Authorization", "KakaoAK 1f5b9a84c7d8338b6ab29ce3065408b2");
    }
}
