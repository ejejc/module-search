package com.example.clients.feign.kakao;

import com.example.clients.feign.config.KakaoFeignConfig;
import com.example.clients.feign.kakao.dto.SearchReq;
import com.example.clients.feign.kakao.dto.SearchRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "kakaoFeignClients", url = "${open-api.kakao.url}", configuration = KakaoFeignConfig.class)
public interface KakaoFeignClient {
    @GetMapping("/v2/search/blog") // 카카오 검색 API 호출
    SearchRes<SearchRes.Blog> searchBlog (@SpringQueryMap SearchReq.Req req);
}
