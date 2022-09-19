package com.study.openapi.redis.service;

import org.springframework.stereotype.Service;

@Service
public interface ApiResultCacheService {
    Object getApiResultCache(String key);
    void saveApiResultCache(String key, Object response);
}
