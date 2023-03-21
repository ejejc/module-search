package com.example.searchappapi.service;

import com.example.clients.feign.KakaoFeignClient;
import com.example.clients.feign.dto.SearchReq;
import com.example.clients.feign.dto.SearchRes;
import com.example.core.exception.CommonException;
import com.example.domain.common.HttpStatusCode;
import com.example.searchappapi.domain.RankDto;
import com.example.searchappapi.domain.SearchDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.clients.feign.dto.SearchRes.*;

@Service
@AllArgsConstructor
public class SearchService {
    private final KakaoFeignClient kakaoFeignClient;

    public SearchDto<Blog> searchVlog(SearchReq.Req req) {
       SearchRes<Blog> res = kakaoFeignClient.searchBlog(req);
        SearchDto<Blog> searchBlogDto = new SearchDto<>();
       if (res.getDocuments().size() == 0) {
           return searchBlogDto;
       }
       searchBlogDto.makeSearchDto(res, req.getPage());
       return searchBlogDto;
    }

    public RankDto searchPopularQuery() {
        return null;
    }
}
