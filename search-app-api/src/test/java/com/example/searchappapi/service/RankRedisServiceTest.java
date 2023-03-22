package com.example.searchappapi.service;

import com.example.moduleredis.domain.RankDto;
import com.example.moduleredis.service.RankRedisService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = {"spring.redis.port=6379", "spring.redis.host=localhost", "open-api.kakao.url=https://dapi.kakao.com", "spring.mvc.pathmatch.matching-strategy=ant_path_matcher"})
class RankRedisServiceTest {
    @Autowired
    RankRedisService rankRedisService;
    final String REDIS_SEARCH_KEY = "search:ranking";
    final int START_INDEX = 0;
    int END_INDEX = 9;
    Map<String, Integer> fixtureMap;

    @BeforeEach
    public void 캐시_초기화후_검색어_초기값_등록() {
        rankRedisService.deleteRedisKey(REDIS_SEARCH_KEY);
        fixtureMap = new HashMap();
        fixtureMap.put("커피", 1); fixtureMap.put("카페", 2); fixtureMap.put("강아지",3);
        fixtureMap.put("고양이", 4); fixtureMap.put("곰", 5); fixtureMap.put("사자", 6);
        fixtureMap.put("호랑이", 7); fixtureMap.put("개구리", 8); fixtureMap.put("사슴", 9);
        fixtureMap.put("독수리", 10); fixtureMap.put("비둘기", 11);
        for (String key : fixtureMap.keySet()) {
            for (int i=0; i<fixtureMap.get(key);i++) {
                rankRedisService.rankUpToQuery(key, "search:ranking", 1);
            }
        }
    }


    @Test
    void 검색어를_등록하고_레디스에_등록되었는지_확인한다() {
        // when
        List<RankDto> rankReverseOrder = rankRedisService.findRankReverseOrder(REDIS_SEARCH_KEY, START_INDEX, END_INDEX);

        // then
        Assertions.assertThat(rankReverseOrder.size()).isEqualTo(END_INDEX+1);
    }

    @Test
    void 검색횟수가_높은_검색어부터_출력되는지_확인한다() {
        // given
        List<String> listKeySet = new ArrayList<>(fixtureMap.keySet());
        Collections.sort(listKeySet, (v1, v2) -> (fixtureMap.get(v2).compareTo(fixtureMap.get(v1))));

        // when
        List<RankDto> rankReverseOrder = rankRedisService.findRankReverseOrder(REDIS_SEARCH_KEY, START_INDEX, END_INDEX);

        // then
        for (int i=0; i<rankReverseOrder.size(); i++) {
            Assertions.assertThat(listKeySet.get(i)).isEqualTo((rankReverseOrder.get(i).getQuery()));
            Assertions.assertThat(fixtureMap.get(listKeySet.get(i))).isEqualTo(rankReverseOrder.get(i).getSearchCnt().intValue());
        }
    }

    @Test
    void 검색어가_10개_이상일때_최대_10개의_검색_키워드가_출력되는지_확인한다() {
        // givne
        List<String> listKeySet = new ArrayList<>(fixtureMap.keySet());
        Collections.sort(listKeySet, (v1, v2) -> (fixtureMap.get(v2).compareTo(fixtureMap.get(v1))));

        // when
        List<RankDto> rankReverseOrder = rankRedisService.findRankReverseOrder(REDIS_SEARCH_KEY, START_INDEX, END_INDEX);

        // then
        List<String> queryList = rankReverseOrder.stream().map(RankDto::getQuery).collect(Collectors.toList());
        Assertions.assertThat(queryList.contains(listKeySet.get(listKeySet.size()-1))).isFalse();
        Assertions.assertThat(queryList.get(START_INDEX)).isEqualTo(listKeySet.get(START_INDEX));
        Assertions.assertThat(queryList.get(END_INDEX)).isEqualTo(listKeySet.get(END_INDEX));
    }

}