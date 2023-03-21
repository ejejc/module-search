package com.example.clients.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class SearchRes<T> {

    private List<T> documents;
    private Meta meta;

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
        private String dateTime;
    }


    @Getter
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Meta {
        private Integer totalCount;
        private Integer pageableCount;
        private Boolean isEnd;
    }

    @Getter
    @Setter
    public static class Error {
        private String errorType;
        private String message;
    }
}
