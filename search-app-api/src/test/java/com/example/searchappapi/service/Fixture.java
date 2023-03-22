package com.example.searchappapi.service;

import com.example.clients.feign.kakao.dto.SearchReq;
import com.example.clients.feign.kakao.dto.SearchRes;

import java.util.ArrayList;
import java.util.List;

public class Fixture {

    public static SearchRes<SearchRes.Blog> generateSearchResBlog(SearchReq.Req req) {
        List<SearchRes.Blog> list = new ArrayList<>();
        for (int i=0; i<req.getSize(); i++) {
            list.add(new SearchRes.Blog());
        }
        SearchRes<SearchRes.Blog> searchRes = new SearchRes<>();
        searchRes.setDocuments(list);
        SearchRes.Meta meta = new SearchRes.Meta();
        meta.testOf(10000, 800, false);
        searchRes.setMeta(meta);
        return searchRes;
    }
}
