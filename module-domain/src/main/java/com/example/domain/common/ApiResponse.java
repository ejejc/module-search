package com.example.domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiResponse<T> {
    private T data;
    private Meta meta;

    @AllArgsConstructor
    @Getter
    public static class Meta {
        private int status;
        private String code;
        private String message;
    }


    private ApiResponse(final T data) {
        this.data = data;
    }


    public static<T> ApiResponse<T> createOk(final T data) {
        return new ApiResponse<>(data);
    }

    public static ApiResponse<Void> createError(HttpStatusCode httpStatusCode, String message) {
        return new ApiResponse<>(null, new Meta(httpStatusCode.getStatus(), httpStatusCode.getCode(), message));
    }
}
