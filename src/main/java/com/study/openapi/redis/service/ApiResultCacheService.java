package com.study.openapi.redis.service;

import com.study.openapi.redis.repository.ApiResultCacheRepository;
import com.study.openapi.redis.repository.SearchResponseDao;
import com.study.openapi.search.dto.SearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
//TODO searchRequet, serachResponse에 의존하는 것 수정
public class ApiResultCacheService<T> {
    private final ApiResultCacheRepository apiResultCacheRepository;
    private final SearchResponseDao searchResponseDao;
    public SearchResponse<T> getApiResultCache(String key) {
        return searchResponseDao.findById(key);
    }

    public void saveApiResultCache(String key, SearchResponse<T> response) {
        searchResponseDao.addItem(response, key);
    }
}
