package com.example.clients.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class SearchDto {

    @Getter
    @Setter
    public static class Req {

        private String query;

        private SortType sort;

        private Integer page = 1;

        private Integer size = 10;
    }

    public static class Res {
        private List<Map<String,Object>> documents;
        private Meta meta;
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

    // TODO: 일단 여기다가 놓아보고 해결해보짜 ~
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static enum SortType {
        ACCURACY("accuracy", "정확도순")
        , RECENCY("recency", "최신순");

        private String type;
        private String desc;
    }

}
