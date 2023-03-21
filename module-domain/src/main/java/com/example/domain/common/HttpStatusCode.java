package com.example.domain.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum HttpStatusCode {
    OK(200, "SU000", "OK"),
    BAD_REQUEST(400, "CL000", "Bad Request"),
    INVALID_PARAMETER(422, "CL022", "invalid parameter");

    private final int status;
    private final String code;
    private final String defaultMessage;

    public static HttpStatusCode findStatusCode(int statusCode) {
        return Arrays.stream(HttpStatusCode.values())
                .filter(vo -> vo.getStatus() == statusCode)
                .findFirst()
                .orElse(null);
    }
}
