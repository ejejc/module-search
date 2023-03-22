package com.example.searchappapi.service;

import com.example.clients.feign.kakao.KakaoFeignClient;
import com.example.clients.feign.kakao.dto.SearchReq;
import com.example.clients.feign.kakao.dto.SearchRes;
import com.example.core.exception.KakaoClientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = {"spring.redis.port=6379", "spring.redis.host=localhost", "open-api.kakao.url=https://dapi.kakao.com", "spring.mvc.pathmatch.matching-strategy=ant_path_matcher"})
public class KakaoFeignClientTest {

    @Autowired
    KakaoFeignClient kakaoFeignClient;
    SearchReq.Req kakaoSearchReq;

    @BeforeEach
    void 카카오_검색API_요청_객체_셋팅한다() {
        kakaoSearchReq = SearchReq.Req.builder()
                .query("카카오")
                .sort(SearchReq.SortType.ACCURACY.getType())
                .page(1)
                .size(10).build();
    }

    @Test
    void 카카오_블로그_API와_통신한여_요청한_사이즈만큼_응답을_반환한다() {
        // when
        SearchRes<SearchRes.Blog> searchBlogRes = kakaoFeignClient.searchBlog(kakaoSearchReq);

        // then
        assertThat(searchBlogRes.getDocuments().size()).isEqualTo(kakaoSearchReq.getSize());
    }

    @Test
    void 필수값인_검색어를_입력하지_않았을경우_에러를_반환한다() {
        // when
        kakaoSearchReq.setQuery(null);

        // then
        assertThrows(KakaoClientException.class, () -> {
            kakaoFeignClient.searchBlog(kakaoSearchReq);
        });
    }
}
