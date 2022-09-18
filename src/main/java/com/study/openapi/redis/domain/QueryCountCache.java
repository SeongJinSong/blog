package com.study.openapi.redis.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisHash;

import org.springframework.data.annotation.Id;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("query-count")
@ToString
public class QueryCountCache {
    @Id
    private String query;
    @Builder.Default
    private AtomicLong counter = new AtomicLong();
    public QueryCountCache increaseCount(){
        counter.incrementAndGet();
        log.info("#### count:"+counter.get());
        return this;
    }
}
