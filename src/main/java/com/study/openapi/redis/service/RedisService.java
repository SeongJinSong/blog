package com.study.openapi.redis.service;

import com.study.openapi.redis.domain.QueryCountCache;
import com.study.openapi.redis.repository.QueryCountCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RequiredArgsConstructor
@Service
@ConditionalOnProperty(prefix = "spring.redis.database", value = "host")
public class RedisService {
    private final QueryCountCacheRepository queryCountCacheRepository;
    public void inCreateCount(String query){
        queryCountCacheRepository.findById(query)
                .ifPresentOrElse(
                        queryCountCache -> queryCountCacheRepository.save(queryCountCache.increaseCount())
                , ()->queryCountCacheRepository.save(QueryCountCache.builder().query(query).count(1).build()));
    }
}
