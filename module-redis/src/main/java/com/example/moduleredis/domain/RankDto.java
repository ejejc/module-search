package com.example.moduleredis.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
//@ApiModel(value = "인기 검색어 응답 DTO")
public class RankDto {

    //@ApiModelProperty(value = "검색어")
    private String query;
    //@ApiModelProperty(value = "검색 횟수")
    private Double searchCnt;

    public static RankDto convertToResponseRankingDto(ZSetOperations.TypedTuple<String> tuple) {
        return RankDto.builder()
                .query(tuple.getValue())
                .searchCnt(tuple.getScore()).build();
    }
}
