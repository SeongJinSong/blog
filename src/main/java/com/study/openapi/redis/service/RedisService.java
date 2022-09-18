package com.study.openapi.redis.service;

import com.study.openapi.documents.Blog;
import com.study.openapi.global.common.SearchResponse;
import com.study.openapi.redis.domain.ApiResultCache;
import com.study.openapi.redis.domain.QueryCountCache;
import com.study.openapi.redis.repository.ApiResultCacheRepository;
import com.study.openapi.redis.repository.QueryCountCacheRepository;
import com.study.openapi.redis.repository.SearchResponseDao;
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
//TODO RedisService 자체가 <T> 에 의존적이어도 될까?
public class RedisService <T> {
    private final QueryCountCacheRepository queryCountCacheRepository;
    private final ApiResultCacheRepository apiResultCacheRepository;
    private final SearchResponseDao searchResponseDao;
    public void inCreateCount(String query){
        queryCountCacheRepository.findById(query)
                .ifPresentOrElse(cache->queryCountCacheRepository.save(cache.increaseCount())
                , ()->queryCountCacheRepository.save(QueryCountCache.builder().query(query).build().increaseCount()));
    }

    public SearchResponse<T> getApiResultCache(String key) {
        return searchResponseDao.findById(key);
    }

    public void saveApiResultCache(String key, SearchResponse<T> response) {
        searchResponseDao.addItem(response, key);
    }
}
