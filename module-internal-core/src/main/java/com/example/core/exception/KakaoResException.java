package com.example.core.exception;

import com.example.domain.common.HttpStatusCode;
import lombok.Getter;

@Getter
public class KakaoResException extends CommonException{

    private String errorType;

    public KakaoResException(HttpStatusCode httpStausCode, String message, String errorType) {
        super(httpStausCode, message);
        this.errorType = errorType;
    }
}
