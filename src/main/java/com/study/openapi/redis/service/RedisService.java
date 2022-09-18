package com.study.openapi.redis.service;

import com.study.openapi.documents.Blog;
import com.study.openapi.global.common.SearchResponse;
import com.study.openapi.redis.domain.ApiResultCache;
import com.study.openapi.redis.domain.QueryCountCache;
import com.study.openapi.redis.repository.ApiResultCacheRepository;
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
    private final ApiResultCacheRepository apiResultCacheRepository;
    public void inCreateCount(String query){
        queryCountCacheRepository.findById(query)
                .ifPresentOrElse(cache->queryCountCacheRepository.save(cache.increaseCount())
                , ()->queryCountCacheRepository.save(QueryCountCache.builder().query(query).build().increaseCount()));
    }

    public SearchResponse<Blog> getApiResultCache(String key) {
        if(apiResultCacheRepository.findById(key).isEmpty())return null;
        else return apiResultCacheRepository.findById(key).get().getResponse();
    }

    public void saveApiResultCache(String key, SearchResponse<Blog> response) {
        apiResultCacheRepository.save(ApiResultCache.builder().request(key).response(response).build());
    }
}
