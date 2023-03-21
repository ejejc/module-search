package com.example.clients.feign;

import com.example.clients.feign.config.KaKaoFeignConfiguration;
import com.example.clients.feign.dto.SearchDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "kakaoFeignClients", url = "${open-api.kakao.url}", configuration = KaKaoFeignConfiguration.class)
public interface KakaoFeignClient {
    SearchDto.Res searchBlog (@RequestParam("query") String query
            , @RequestParam("sort") String sort
            , @RequestParam("page") Integer page
            , @RequestParam("size") Integer size);
}
