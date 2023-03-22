package com.example.clients.feign.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchRes<T> {

    private List<T> documents;
    private Meta meta;

    @Getter
    @ApiModel(value = "블로그 검색 응답 DTO")
    public static class Blog {
        @ApiModelProperty(value = "블로그 글 제목")
        private String title;
        @ApiModelProperty(value = "블로그 글 요약")
        private String contents;
        @ApiModelProperty(value = "블로그 글 URL")
        private String url;
        @ApiModelProperty(value = "블로그의 이름")
        @JsonProperty(value = "blogname")
        private String blogName;
        @ApiModelProperty(value = "검색 시스템에서 추출한 대표 미리보기 이미지 URL")
        @JsonProperty(value = "thumbnail")
        private String thumbNail;
        @ApiModelProperty(value = "블로그 글 작성시간")
        @JsonProperty(value = "datetime")
        private String dateTime;
    }


    @Getter
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Meta {
        private Integer totalCount;
        private Integer pageableCount;
        private Boolean isEnd;

        public void testOf(Integer totalCount, Integer pageableCount, Boolean isEnd) {
            this.totalCount = totalCount;
            this.pageableCount = pageableCount;
            this.isEnd = isEnd;
        }
    }

    @Getter
    @Setter
    public static class Error {
        private String errorType;
        private String message;
    }
}
