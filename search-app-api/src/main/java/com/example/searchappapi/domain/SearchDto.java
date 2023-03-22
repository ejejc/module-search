package com.example.searchappapi.domain;

import com.example.clients.feign.kakao.dto.SearchRes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "검색 RES DTO")
public class SearchDto<T> {
    @ApiModelProperty(value = "결과 페이지 번호, 1~50 사이의 값, 기본 값 1")
    private Integer page = 0;
    @ApiModelProperty(value = "한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10")
    private Integer pageSize = 0;
    @ApiModelProperty(value = "검색된 문서 수")
    private Integer totalSize = 0;
    @ApiModelProperty(value = "검색된 데이터")
    private List<T> searchData;

    public void makeSearchDto(SearchRes<T> res, Integer page) {
        this.page = page;
        this.totalSize = res.getMeta().getTotalCount();
        this.pageSize = res.getDocuments().size();
        this.searchData = res.getDocuments();
    }
}
