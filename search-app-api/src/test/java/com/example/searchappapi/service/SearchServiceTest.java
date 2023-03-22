package com.example.searchappapi.service;

import com.example.clients.feign.kakao.KakaoFeignClient;
import com.example.clients.feign.kakao.dto.SearchReq;
import com.example.clients.feign.kakao.dto.SearchRes;
import com.example.moduleredis.service.RankRedisService;
import com.example.searchappapi.domain.SearchDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(properties = {"spring.redis.port=6379", "spring.redis.host=localhost", "open-api.kakao.url=https://dapi.kakao.com", "spring.mvc.pathmatch.matching-strategy=ant_path_matcher"})
public class SearchServiceTest {
    @InjectMocks
    SearchService searchService;

    @Mock
    KakaoFeignClient kakaoFeignClient;

    @Mock
    RankRedisService rankRedisService;

    SearchRes<SearchRes.Blog> kakaoSearchRes;
    SearchReq.Req kakaoSearchReq;

    @BeforeEach
    void of() {
        kakaoSearchReq = SearchReq.Req.builder()
                .query("카카오")
                .sort(SearchReq.SortType.ACCURACY.getType())
                .page(1)
                .size(10).build();
        kakaoSearchRes = Fixture.generateSearchResBlog(kakaoSearchReq);
    }

    @Test
    @DisplayName("블로그 검색 서비스 테스트 - 정상 응답일 때")
    void searchBlogTest() {
        // given
        Mockito.when(kakaoFeignClient.searchBlog(Mockito.any())).thenReturn(kakaoSearchRes);
        // when
        SearchDto<SearchRes.Blog> blogSearchDto = searchService.searchBlog(kakaoSearchReq);
        // then
        Assertions.assertThat(blogSearchDto.getSearchData().size()).isEqualTo(kakaoSearchReq.getSize());
        Assertions.assertThat(blogSearchDto.getPage()).isEqualTo(kakaoSearchReq.getPage());
        Assertions.assertThat(blogSearchDto.getTotalSize()).isEqualTo(kakaoSearchRes.getMeta().getTotalCount());
    }

    @Test
    @DisplayName("블로그 검색 서비스 테스트 - 응답값이 null일 때")
    void searchBlogNullTest() {
        Mockito.when(kakaoFeignClient.searchBlog(Mockito.any())).thenReturn(null);

        SearchDto<SearchRes.Blog> blogSearchDto = searchService.searchBlog(kakaoSearchReq);

        Assertions.assertThat(blogSearchDto.getPageSize()).isEqualTo(0);
        Assertions.assertThat(blogSearchDto.getPage()).isEqualTo(0);
        Assertions.assertThat(blogSearchDto.getTotalSize()).isEqualTo(0);
    }
}
