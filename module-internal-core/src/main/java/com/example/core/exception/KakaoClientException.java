package com.example.core.exception;

import com.example.domain.common.HttpStatusCode;
import lombok.Getter;

@Getter
public class KakaoClientException extends CommonException{

    private String errorType;

    public KakaoClientException(HttpStatusCode httpStausCode, String message, String errorType) {
        super(httpStausCode, message);
        this.errorType = errorType;
    }
}
