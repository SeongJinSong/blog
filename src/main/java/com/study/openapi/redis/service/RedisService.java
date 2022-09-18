package com.study.openapi.redis.service;

import com.study.openapi.redis.domain.QueryCountCache;
import com.study.openapi.redis.repository.QueryCountCacheRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RequiredArgsConstructor
@Service
@ConditionalOnProperty(prefix = "spring.redis.database", value = "host")
public class RedisService {
    private final QueryCountCacheRepository queryCountCacheRepository;
    public void inCreateCount(String query){
        queryCountCacheRepository.findById(query)
                .ifPresentOrElse(cache->queryCountCacheRepository.save(cache.increaseCount())
                , ()->queryCountCacheRepository.save(QueryCountCache.builder().query(query).build().increaseCount()));
    }
}
