package com.example.moduleredis.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RankDto {
    private String query;
    private Double rank;

    public static RankDto convertToResponseRankingDto(ZSetOperations.TypedTuple<String> tuple) {
        return RankDto.builder()
                .query(tuple.getValue())
                .rank(tuple.getScore()).build();
    }
}
