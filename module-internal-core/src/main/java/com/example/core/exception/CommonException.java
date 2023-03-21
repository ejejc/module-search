package com.example.core.exception;

import com.example.domain.common.HttpStatusCode;
import lombok.Getter;

@Getter
public class CommonException extends RuntimeException{
    private final String message;
    private final HttpStatusCode httpStausCode;

    public CommonException(HttpStatusCode httpStausCode, String message) {
        this.message = message;
        this.httpStausCode = httpStausCode;
    }
}
