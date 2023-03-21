package com.example.clients.feign.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SearchReq {

    @Getter
    @Setter
    public static class Req {

        private String query;
        private SortType sortType;
        private String sort;

        private Integer page = 1;

        private Integer size = 10;

        public void setSortType(SortType sortType) {
            this.sortType = sortType;
            this.sort = this.sortType.ofType(sortType);
        }
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

        public String ofType(SortType type) {
            return Arrays.stream(SortType.values())
                    .filter(vo -> vo.equals(type))
                    .findFirst()
                    .map(SortType::getType)
                    .orElse(null);

        }
    }

}
