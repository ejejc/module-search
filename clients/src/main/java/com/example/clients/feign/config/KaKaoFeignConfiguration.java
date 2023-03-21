package com.example.clients.feign.config;

import com.example.clients.feign.dto.SearchDto;
import com.example.core.exception.KakaoResException;
import com.example.domain.common.HttpStatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;

public class KaKaoFeignConfiguration implements ErrorDecoder  {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("Authorization", "KakaoAK 1f5b9a84c7d8338b6ab29ce3065408b2");
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        SearchDto.Error errorBody = null;
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            errorBody = mapper.readValue(bodyIs, SearchDto.Error.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }

        switch (response.status()) {
            case 400: case 401:
                throw new KakaoResException(HttpStatusCode.findStatusCode(response.status()), errorBody.getMessage(), errorBody.getErrorType());
        }
        return null;
    }
}
