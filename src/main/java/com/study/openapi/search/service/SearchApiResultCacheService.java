package com.study.openapi.search.service;

import com.study.openapi.redis.service.ApiResultCacheService;
import com.study.openapi.search.dto.SearchResponse;
import com.study.openapi.search.repository.SearchResponseDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SearchApiResultCacheService implements ApiResultCacheService {
    private final SearchResponseDao searchResponseDao;
    public SearchResponse getApiResultCache(String key) {
        return searchResponseDao.findById(key);
    }

    @Override
    public void saveApiResultCache(String key, Object response) {
        searchResponseDao.addItem(response, key);
    }
}
