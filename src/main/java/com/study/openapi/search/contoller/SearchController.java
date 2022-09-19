package com.study.openapi.search.contoller;

import com.study.openapi.documents.Blog;
import com.study.openapi.global.base.ResponseWrapper;
import com.study.openapi.global.common.SearchRequest;
import com.study.openapi.global.common.SearchResponse;
import com.study.openapi.search.contoller.dto.SearchRank;
import com.study.openapi.search.service.SearchRankService;
import com.study.openapi.search.service.SearchHistoryService;
import com.study.openapi.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping(path = "/v2/search/blog")
@RequiredArgsConstructor
@RestController
public class SearchController {
    private final SearchService searchService;
    private final SearchRankService searchRankService;
    private final SearchHistoryService searchHistoryService;

    @GetMapping
    public ResponseEntity<ResponseWrapper<SearchResponse<Blog>>> getBlogList(
            HttpServletRequest httpservletRequest, @Valid SearchRequest request){
        /* TODO
            1. 서비스단 에 HttpServletRequest를 넘겨주는것 수정 필요
            2. 카카오 api에서 실패하는 경우 네이버 api를 사용하는 것을 구현하기위해
            MSA에서 fault tolerance로 많이 사용하는tolaresilience4j를 적용해보자.
         */
        return ResponseWrapper.ok(searchService.getBlogList(httpservletRequest, request), "success");
    }

    /**
     * DB에 저장된 History를 검색어 기반으로 그루핑해 오더링한 결과를 리턴한다.
     *
     * @param pageable default limit = 10
     * @return responseWrapper
     */
    @GetMapping("/rank")
    public ResponseEntity<ResponseWrapper<Page<SearchRank>>> getPopularSearchWord(Pageable pageable){
        return ResponseWrapper.ok(searchHistoryService.getPopularSearchWord(pageable), "success");
    }

    @GetMapping("/rank-new")
    public ResponseEntity<ResponseWrapper<List<SearchRank>>> searchRankList(){
        return ResponseWrapper.ok(searchRankService.searchRankList(), "success");
    }
}
