package com.example.core.exception;

import com.example.domain.common.HttpStatusCode;
import lombok.Getter;

@Getter
public class ServiceUnavailableException extends CommonException {

    public ServiceUnavailableException(HttpStatusCode httpStausCode, String message) {
        super(httpStausCode, message);
    }
}
