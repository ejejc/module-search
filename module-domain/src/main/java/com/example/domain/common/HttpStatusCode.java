package com.example.domain.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum HttpStatusCode {
    OK(200,  "OK"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    INVALID_PARAMETER(422, "invalid parameter"),
    TOO_MANY_REQUEST(429, "Too Many Request"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    BAD_GATEWAY(502, "Bad Gateway"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable");

    private final int status;
    private final String msg;

    public static HttpStatusCode findStatusCode(int statusCode) {
        return Arrays.stream(HttpStatusCode.values())
                .filter(vo -> vo.getStatus() == statusCode)
                .findFirst()
                .orElse(null);
    }
}
