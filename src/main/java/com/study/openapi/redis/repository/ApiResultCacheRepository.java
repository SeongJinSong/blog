package com.study.openapi.redis.repository;

import com.study.openapi.redis.domain.ApiResultCache;
import org.springframework.data.repository.CrudRepository;

public interface ApiResultCacheRepository extends CrudRepository<ApiResultCache, String> {
}
