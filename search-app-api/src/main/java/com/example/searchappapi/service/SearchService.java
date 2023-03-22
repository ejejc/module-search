package com.example.searchappapi.service;

import com.example.clients.feign.kakao.KakaoFeignClient;
import com.example.clients.feign.kakao.dto.SearchReq;
import com.example.clients.feign.kakao.dto.SearchRes;
import com.example.moduleredis.domain.RankDto;
import com.example.moduleredis.service.RankRedisService;
import com.example.searchappapi.domain.SearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

import static com.example.clients.feign.kakao.dto.SearchRes.Blog;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final KakaoFeignClient kakaoFeignClient;
    private final RankRedisService redisRankService;
    private static final String REDIS_SEARCH_KEY = "search:ranking";
    private static final int RANK_INCREMENT_SIZE = 1;
    private static final int START_INDEX = 0;
    private static final int END_INDEX = 9;

    /**
     * blog 검색 기능
     * @param req
     * @return
     */
    public SearchDto<Blog> searchBlog(SearchReq.Req req) {

        // blog 검색 시, Redis 내 검색어 검색 횟수 +1
       redisRankService.rankUpToQuery(req.getQuery(),REDIS_SEARCH_KEY,RANK_INCREMENT_SIZE);

       // 카카오 Search-Api 연동 (블로그 검색)
       SearchRes<Blog> res = kakaoFeignClient.searchBlog(req);
       SearchDto<Blog> searchBlogDto = new SearchDto<>();
       if (Objects.isNull(res) || CollectionUtils.isEmpty(res.getDocuments())) {
           return searchBlogDto; // 기본 값으로 셋팅된 객체 반환
       }

       // 검색 응답 DTO 객체 생성
       searchBlogDto.makeSearchDto(res, req.getPage());

       return searchBlogDto;
    }

    /**
     * 1 ~ 10위까지 인기 검색어 순위 조회 기능
     * @return
     */
    public List<RankDto> searchPopularQuery() {

        // Redis 통신으로 인기 검색어 순위 반환9][0
        return redisRankService.findRankReverseOrder(REDIS_SEARCH_KEY, START_INDEX, END_INDEX);
    }
}
