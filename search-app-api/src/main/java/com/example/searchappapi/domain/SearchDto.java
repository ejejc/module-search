package com.example.searchappapi.domain;

import com.example.clients.feign.dto.SearchRes;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto<T> {
    private Integer page = 0;
    private Integer pageSize = 0;
    private Integer totalSize = 0;

    private List<T> searchData;

    public void makeSearchDto(SearchRes<T> res, Integer page) {
        this.page = page;
        this.totalSize = res.getMeta().getTotalCount();
        this.pageSize = res.getDocuments().size();
        this.searchData = res.getDocuments();
    }
}
