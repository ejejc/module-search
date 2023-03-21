package com.example.searchappapi.controller;

import com.example.clients.feign.dto.SearchReq;
import com.example.clients.feign.dto.SearchRes;
import com.example.domain.common.ApiResponse;
import com.example.searchappapi.domain.RankDto;
import com.example.searchappapi.domain.SearchDto;
import com.example.searchappapi.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.clients.feign.dto.SearchRes.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/blog")
    public ApiResponse<SearchDto<Blog>> searchVlog(SearchReq.Req req) {
        return ApiResponse.createOk(searchService.searchVlog(req));
    }

    @GetMapping("/popular/query")
    public ApiResponse<RankDto> searchPopularQuery() {
        return ApiResponse.createOk(searchService.searchPopularQuery());
    }
}
