package com.example.searchappapi.exception;

import com.example.core.exception.KakaoResException;
import com.example.domain.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
@RequiredArgsConstructor
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = KakaoResException.class)
    public ApiResponse<Void> kakaoErrorHandler(KakaoResException e)
    {
        log.error(e.getMessage(), e);
        return ApiResponse.createError(e.getHttpStausCode(), e.getMessage());
    }
}
