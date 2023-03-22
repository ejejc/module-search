package com.example.core.exception;

import com.example.domain.common.HttpStatusCode;
import lombok.Getter;

@Getter
public class KakaoServerException extends CommonException{
    private String errorType;

    public KakaoServerException(HttpStatusCode httpStausCode, String message, String errorType) {
        super(httpStausCode, message);
        this.errorType = errorType;
    }
}
