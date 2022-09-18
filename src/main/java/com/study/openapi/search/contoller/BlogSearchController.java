package com.study.openapi.search.contoller;

import com.study.openapi.documents.Blog;
import com.study.openapi.global.base.ResponseWrapper;
import com.study.openapi.global.common.ApiService;
import com.study.openapi.global.common.SearchRequest;
import com.study.openapi.global.common.SearchResponse;
import com.study.openapi.redis.service.RedisService;
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
import java.util.List;

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
    private final RedisService redisService;
    private final ApiService<Blog> apiService;

    @GetMapping
    public ResponseEntity<ResponseWrapper<SearchResponse<Blog>>> getBlogList(
            HttpServletRequest httpservletRequest, @Valid SearchRequest request){
        log.info("request : {}", request);
        //TODO request를 저장은 추 후  AOP로 적용 또는 threadPoolExecutor 별도로 빼서 비동기로 처리
        searchService.saveRequest(request);

        //TODO searchService가 알아야할 메타
        String host = "https://dapi.kakao.com";
        String key = host+"/"+httpservletRequest.getRequestURI()+"?"+httpservletRequest.getQueryString();

        //TODO @Cacheable을 적용하여 분기 삭제
        SearchResponse<Blog> response = redisService.getApiResultCache(key);
        if(response==null){
            //api 호출
            System.out.println("@@@@@@@ api 직접 호출");
            response = apiService.call(host, httpservletRequest, request);
            //레디스에 api 검색결과 저장
            redisService.saveApiResultCache(key, response);
        }

        //레디스에 카운트 설정
        //TODO LongAdder, Accumulator 적용 -> zset 적용
        redisService.inCreateCount(request.getQuery());
        //TODO redisconfig에서 redis가 뜰때, 집계함수를 통해 count 집계
        //TODO 레디스에 카운트 센것 주기적으로 db에 저장 혹은 정합성 검사
        
        return ResponseWrapper.ok(response, "success");
    }
    @GetMapping("/rank")
    public ResponseEntity<ResponseWrapper<Page<SearchRank>>> getPopularSearchWord(Pageable pageable){
        return ResponseWrapper.ok(searchService.getPopularSearchWord(pageable), "success");
    }
}
