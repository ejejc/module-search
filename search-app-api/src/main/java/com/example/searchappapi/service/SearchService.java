package com.example.searchappapi.service;

import com.example.clients.feign.KakaoFeignClient;
import com.example.clients.feign.dto.SearchReq;
import com.example.clients.feign.dto.SearchRes;
import com.example.moduleredis.domain.RankDto;
import com.example.moduleredis.service.RankRedisService;
import com.example.searchappapi.domain.SearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

import static com.example.clients.feign.dto.SearchRes.Blog;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final KakaoFeignClient kakaoFeignClient;
    private final RankRedisService redisRankService;
    private static final String REDIS_SEARCH_KEY = "search:ranking";
    private static final int RANK_INCREMENT_SIZE = 1;
    private static final int START_INDEX = 0;
    private static final int END_INDEX = 9;

    public SearchDto<Blog> searchVlog(SearchReq.Req req) {
       redisRankService.RankUpToQuery(req.getQuery(),REDIS_SEARCH_KEY,RANK_INCREMENT_SIZE);

       SearchRes<Blog> res = kakaoFeignClient.searchBlog(req);
       SearchDto<Blog> searchBlogDto = new SearchDto<>();
       if (Objects.isNull(res) || CollectionUtils.isEmpty(res.getDocuments())) {
           return searchBlogDto;
       }
       searchBlogDto.makeSearchDto(res, req.getPage());
       return searchBlogDto;
    }

    public List<RankDto> searchPopularQuery() {
        return redisRankService.findRankReverseOrder(REDIS_SEARCH_KEY, START_INDEX, END_INDEX);
    }
}
