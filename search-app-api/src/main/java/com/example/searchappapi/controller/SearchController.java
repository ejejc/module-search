package com.example.searchappapi.controller;

import com.example.clients.feign.kakao.dto.SearchReq;
import com.example.domain.common.ApiResponse;
import com.example.moduleredis.domain.RankDto;
import com.example.searchappapi.domain.SearchDto;
import com.example.searchappapi.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.clients.feign.kakao.dto.SearchRes.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
@Api(tags = {"검색 Controller"})
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/blog")
    @ApiOperation(value = "블로그 검색 API")
    public ApiResponse<SearchDto<Blog>> searchVlog(SearchReq.Req req) {
        return ApiResponse.createOk(searchService.searchBlog(req));
    }

    @GetMapping("/popular/query")
    @ApiOperation(value = "인기 검색어 조회 API")
    public ApiResponse<List<RankDto>> searchPopularQuery() {
        return ApiResponse.createOk(searchService.searchPopularQuery());
    }
}
