package com.example.clients.feign.config;

import com.example.clients.feign.dto.SearchReq;
import com.example.clients.feign.dto.SearchRes;
import com.example.core.exception.KakaoResException;
import com.example.domain.common.HttpStatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.io.InputStream;


public class KaKaoFeignConfiguration implements ErrorDecoder  {


    @Override
    public Exception decode(String methodKey, Response response) {
        SearchRes.Error errorBody = null;
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            errorBody = mapper.readValue(bodyIs, SearchRes.Error.class);
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
