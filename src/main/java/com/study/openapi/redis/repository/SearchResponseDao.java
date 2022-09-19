package com.study.openapi.redis.repository;

import com.study.openapi.search.dto.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class SearchResponseDao<T>{
    private final RedisTemplate<String, Object> redisTemplate;

    public void addItem(SearchResponse searchResponse, String key){
        redisTemplate.opsForValue().set(key, searchResponse);
        redisTemplate.expire(key, 10, TimeUnit.SECONDS);
    }

    public SearchResponse findById(String key){
        return (SearchResponse) redisTemplate.opsForValue().get(key);
    }
}
