package com.study.openapi.redis.domain;

import com.study.openapi.search.contents.Blog;
import com.study.openapi.global.common.SearchResponse;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Slf4j
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash(value="api-result", timeToLive = 5L)
@ToString
public class ApiResultCache {
    @Id
    private String request;
    //TODO 이부분을 search 모듈과 의존성을 제거하자
    private SearchResponse<Blog> response;
}
