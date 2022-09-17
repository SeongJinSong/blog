package com.study.openapi.search.contoller;

import com.study.openapi.documents.Blog;
import com.study.openapi.global.base.ResponseWrapper;
import com.study.openapi.global.common.ApiService;
import com.study.openapi.global.common.SearchRequest;
import com.study.openapi.global.common.SearchResponse;
import com.study.openapi.search.contoller.dto.SearchRank;
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
public class BlogSearchController {
    private final SearchService searchService;
    private final ApiService<Blog> apiService;

    @GetMapping
    public ResponseEntity<ResponseWrapper<SearchResponse<Blog>>> getBlogList(
            HttpServletRequest httpservletRequest, @Valid SearchRequest request){
        log.info("request : {}", request);
        //request를 저장은 추 후  AOP로 적용
        searchService.saveRequest(request);
        
        //api 호출
        SearchResponse<Blog> list = apiService.call("https://dapi.kakao.com", httpservletRequest, request);

        //레디스에 카운트 설정 - 분산락적용
        //레디스가 뜰때, 집계함수를 통해 count 집계
        //레디스에 카운트 센것 주기적으로 db에 저장
        
        return ResponseWrapper.ok(list, "success");
    }
    @GetMapping("/rank")
    public ResponseEntity<ResponseWrapper<Page<SearchRank>>> getPopularSearchWord(Pageable pageable){
        return ResponseWrapper.ok(searchService.getPopularSearchWord(pageable), "success");
    }
}
