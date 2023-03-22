package com.example.searchappapi.exception;

import com.example.core.exception.CommonException;
import com.example.core.exception.KakaoClientException;
import com.example.core.exception.KakaoServerException;
import com.example.core.exception.ServiceUnavailableException;
import com.example.domain.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
@RequiredArgsConstructor
public class ApiExceptionHandler {

    @ExceptionHandler(value = CommonException.class)
    public ApiResponse<Void> commonException(CommonException e)
    {
        log.error("[Search-Api] Error msg: {}", e.getMessage());
        return ApiResponse.createError(e.getHttpStausCode(), e.getMessage());
    }

    @ExceptionHandler(value = KakaoClientException.class)
    public ApiResponse<Void> kakaoClientException(KakaoClientException e)
    {
        log.error("[Kakao-Api] Client Error msg: {}", e.getMessage());
        return ApiResponse.createError(e.getHttpStausCode(), e.getMessage());
    }

    @ExceptionHandler(value = KakaoServerException.class)
    public ApiResponse<Void> kakaoServerException(KakaoServerException e)
    {
        log.error("[Kakao-Api] Server Error msg: {}", e.getMessage());
        return ApiResponse.createError(e.getHttpStausCode(), e.getMessage());
    }

    @ExceptionHandler(value = ServiceUnavailableException.class)
    public ApiResponse<Void> serviceUnavailableException (ServiceUnavailableException e)
    {
        log.error("[Search-Api] Server Error msg: {}", e.getMessage());
        return ApiResponse.createError(e.getHttpStausCode(), e.getMessage());
    }
}
