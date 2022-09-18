package com.study.openapi.redis.domain;

import com.study.openapi.documents.Blog;
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
    private SearchResponse<Blog> response;
}
