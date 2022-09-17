package com.study.openapi.search.contoller;

import com.study.openapi.global.base.ResponseWrapper;
import com.study.openapi.global.common.SearchRequest;
import com.study.openapi.global.common.SearchResponse;
import com.study.openapi.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public ResponseEntity<ResponseWrapper<SearchResponse>> getBlogList(@Valid SearchRequest request){
        log.info("request : {}", request);
        //request를 저장은 추 후  AOP로 적용
        searchService.saveRequest(request);
        
        //api 호출
        //레디스에 카운트 설정 - 분산락적용

        //레디스에 카운트 센것 주기적으로 db에 저장
        
        return ResponseWrapper.ok(null, "success");
    }
}
