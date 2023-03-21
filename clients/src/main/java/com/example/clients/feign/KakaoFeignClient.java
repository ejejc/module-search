package com.example.clients.feign;

import com.example.clients.feign.config.KakaoFeignConfig;
import com.example.clients.feign.dto.SearchReq;
import com.example.clients.feign.dto.SearchRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "kakaoFeignClients", url = "${open-api.kakao.url}", configuration = KakaoFeignConfig.class)
public interface KakaoFeignClient {
    @GetMapping("/v2/search/blog")
    SearchRes<SearchRes.Blog> searchBlog (@SpringQueryMap SearchReq.Req req);
}
