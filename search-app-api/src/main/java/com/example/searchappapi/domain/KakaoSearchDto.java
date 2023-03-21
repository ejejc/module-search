package com.example.searchappapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class KakaoSearchDto {
    @Getter
    public static class Blog {
        private String title;
        private String contents;
        private String url;
        @JsonProperty(value = "blogname")
        private String blogName;
        @JsonProperty(value = "thumbnail")
        private String thumbnail;
        @JsonProperty(value = "datetime")
        private LocalDateTime dateTime;
    }
}
