package com.study.openapi.search.repository;

import com.study.openapi.redis.repository.ResponseDao;
import com.study.openapi.search.dto.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class SearchResponseDao implements ResponseDao {
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void addItem(Object response, String key) {
        redisTemplate.opsForValue().set(key, response);
        redisTemplate.expire(key, 10, TimeUnit.SECONDS);
    }

    public SearchResponse findById(String key){
        return (SearchResponse) redisTemplate.opsForValue().get(key);
    }
}
