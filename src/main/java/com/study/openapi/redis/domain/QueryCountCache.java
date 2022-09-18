package com.study.openapi.redis.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisHash;

import org.springframework.data.annotation.Id;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("query-count")
public class QueryCountCache {
    @Id
    private String query;
    private long count;
    public QueryCountCache increaseCount(){
        count++;
        return this;
    }
}
