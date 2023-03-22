package com.example.clients.feign.config;

import com.example.clients.feign.dto.SearchRes;
import com.example.core.exception.CommonException;
import com.example.core.exception.KakaoClientException;
import com.example.core.exception.KakaoServerException;
import com.example.core.exception.ServiceUnavailableException;
import com.example.domain.common.HttpStatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class KaKaoFeignConfiguration implements ErrorDecoder  {
    @Override
    public Exception decode(String methodKey, Response response) {
        log.error("[Kakao-Api] 응답 오류 request: {}", response.request());
        SearchRes.Error errorBody = null;
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            errorBody = mapper.readValue(bodyIs, SearchRes.Error.class);
        } catch (IOException e) {
            log.error("[Kakao-Api] 오류 응답 값 파싱 에러: {}", e.getMessage());
            return new CommonException(HttpStatusCode.INTERNAL_SERVER_ERROR, "내부 오류가 발생했습니다. 잠시 후에 다시 시도해주세요.");
        }

        HttpStatus status = HttpStatus.valueOf(response.status());
        if (status.is4xxClientError()) {
            return new KakaoClientException(HttpStatusCode.findStatusCode(response.status()), errorBody.getMessage(), errorBody.getErrorType());

        } else if (status.is5xxServerError()) {
            return new KakaoServerException(HttpStatusCode.findStatusCode(response.status()), errorBody.getMessage(), errorBody.getErrorType());
        }

        return new ServiceUnavailableException(HttpStatusCode.INTERNAL_SERVER_ERROR, "내부 오류가 발생했습니다. 잠시 후에 다시 시도해주세요.");
    }
}
