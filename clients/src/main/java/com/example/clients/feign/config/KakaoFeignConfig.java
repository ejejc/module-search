package com.example.clients.feign.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class KakaoFeignConfig {

    @Bean // 카카오 클라이언트 ErrorDecoder 빈 등록
    public KaKaoFeignConfiguration errorDecoder() {
        return new KaKaoFeignConfiguration();
    }

    @Bean // 카카오 인증 Key 빈 등록
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("Authorization", "KakaoAK 1f5b9a84c7d8338b6ab29ce3065408b2");
    }
}
