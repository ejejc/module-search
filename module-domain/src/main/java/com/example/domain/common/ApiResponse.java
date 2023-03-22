package com.example.domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ApiResponse<T> {
    private Meta meta;
    private T data;

    public ApiResponse(Meta meta) {
        this.meta = meta;
    }

    @AllArgsConstructor
    @Getter
    public static class Meta {
        private int status;
        private String message;
    }

    public static<T> ApiResponse<T> createOk(final T data) {
        return new ApiResponse<>(new Meta(HttpStatusCode.OK.getStatus(), HttpStatusCode.OK.getMsg()), data);
    }

    public static ApiResponse<Void> createError(HttpStatusCode httpStatusCode, String message) {
        return new ApiResponse<>(new Meta(httpStatusCode.getStatus(), message));
    }
}
