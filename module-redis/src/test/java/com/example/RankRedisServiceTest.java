package com.example;

import org.assertj.core.api.Assertions;
import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.properties.PropertyMapping;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = {"spring.redis.port=6379", "spring.redis.host=localhost", "spring.mvc.pathmatch.matching-strategy=ant_path_matcher"})
@WebAppConfiguration
class RankRedisServiceTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    final String REDIS_RANKING_KEY = "search:ranking";

    @BeforeEach
    void RankUpToQueryTest() {
        stringRedisTemplate.opsForZSet().incrementScore(REDIS_RANKING_KEY, "커피", 1);
    }

    @Test
    void 검색어가_검색된_횟수만큼_저장되는지_확인한다() {
        ZSetOperations zSetOperations = stringRedisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> typedTuples
                = zSetOperations.reverseRangeWithScores(REDIS_RANKING_KEY, 0, 9);
        Assertions.assertThat(typedTuples.size()).isEqualTo(1);
    }

}