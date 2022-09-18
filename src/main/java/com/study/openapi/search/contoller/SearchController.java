package com.study.openapi.search.contoller;

import com.study.openapi.documents.Blog;
import com.study.openapi.global.base.ResponseWrapper;
import com.study.openapi.global.common.ApiService;
import com.study.openapi.global.common.SearchRequest;
import com.study.openapi.global.common.SearchResponse;
import com.study.openapi.redis.service.RedisService;
import com.study.openapi.search.contoller.dto.SearchRank;
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

/**
 * Host : dapi.kakao.com
 * Authorization: KakaoAK ${REST_API_KEY:9ccac738a217f2aa9d006c01900809cc}
 */
@Slf4j
@RequestMapping(path = "/v2/search/blog")
@RequiredArgsConstructor
@RestController
public class SearchController {
    private final SearchService searchService;
    private final SearchHistoryService searchHistoryService;

    @GetMapping
    public ResponseEntity<ResponseWrapper<SearchResponse<Blog>>> getBlogList(
            HttpServletRequest httpservletRequest, @Valid SearchRequest request){
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
}
