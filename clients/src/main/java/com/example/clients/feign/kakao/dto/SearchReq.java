package com.example.clients.feign.kakao.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Arrays;

@ApiModel(value = "검색 REQ DTO")
public class SearchReq {

    @Getter
    @Setter
    @ApiModel(value = "검색 요청 DTO")
    public static class Req {

        @ApiModelProperty(value = "검색어")
        private String query;
        @ApiModelProperty(value = "정렬순서 Enum")
        private SortType sortType;
        @ApiModelProperty(value = "정렬순서")
        private String sort;

        @ApiModelProperty(value = "결과 페이지 번호, 1~50 사이의 값, 기본 값 1")
        private Integer page = 1;

        @ApiModelProperty(value = "한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10")
        private Integer size = 10;

        public void setSortType(SortType sortType) {
            this.sortType = sortType;
            this.sort = this.sortType.ofType(sortType);
        }

        @Builder
        public Req(String query, SortType sortType, String sort, Integer page, Integer size) {
            this.query = query;
            this.sortType = sortType;
            this.sort = sort;
            this.page = page;
            this.size = size;
        }
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static enum SortType {
        ACCURACY("accuracy", "정확도순")
        , RECENCY("recency", "최신순");

        private String type;
        private String desc;

        public String ofType(SortType type) {
            return Arrays.stream(SortType.values())
                    .filter(vo -> vo.equals(type))
                    .findFirst()
                    .map(SortType::getType)
                    .orElse(null);

        }
    }

}
