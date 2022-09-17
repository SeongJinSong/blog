package com.study.openapi.global.common;

import com.study.openapi.documents.Blog;
import com.study.openapi.global.exception.OpenApiCallException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiService {
    private final WebClientRequester webClientRequester;
    private String HOST = "https://dapi.kakao.com";
    private String API = "/v2/search/blog";
    public SearchResponse<Blog> call(SearchRequest request) {
        log.info("ApiService.call");
        //TODO 파싱이 안되는 이유 찾기
        SearchResponse searchResponse = Optional.ofNullable(webClientRequester.getWebClient(HOST + API + "?query=" + request.getQuery()).bodyToMono(SearchResponse.class).block())
                .orElseThrow(() -> new OpenApiCallException());
        String str = Optional.ofNullable(webClientRequester.getWebClient(HOST + API + "?query=" + request.getQuery()).bodyToMono(String.class).block())
                .orElseThrow(() -> new OpenApiCallException());
        log.info("## searchResponse:{}", searchResponse);
        log.info("## string:{}", str);
        return null;
    }



}
