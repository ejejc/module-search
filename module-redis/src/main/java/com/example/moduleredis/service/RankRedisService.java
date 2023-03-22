package com.example.moduleredis.service;

import com.example.moduleredis.domain.RankDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankRedisService {
    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 검색어의 검색 횟수를 incrementSize만큼 증가
     * @param query
     * @param key
     * @param incrementSize
     */
    public void rankUpToQuery(String query, String key, int incrementSize) {
        stringRedisTemplate.opsForZSet().incrementScore(key, query, incrementSize);
    }

    /**
     * startIdx ~ endIdx 까지의 인기 검색어 출력
     * @param key
     * @param startIdx
     * @param endIdx
     * @return
     */
    public List<RankDto> findRankReverseOrder(String key, int startIdx, int endIdx) {
        ZSetOperations ZSetOperations = stringRedisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>>typedTuples
                = ZSetOperations.reverseRangeWithScores(key, startIdx, endIdx);
        if (Objects.isNull(typedTuples)) return null;

        return typedTuples.stream()
                .map(RankDto::convertToResponseRankingDto)
                .collect(Collectors.toList());
    }

    public void deleteRedisKey(String key) {
        stringRedisTemplate.delete(key);
    }
}
