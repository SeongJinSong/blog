package com.study.openapi.redis.repository;

import com.study.openapi.redis.domain.QueryCountCache;
import org.springframework.data.repository.CrudRepository;

public interface QueryCountCacheRepository extends CrudRepository<QueryCountCache, String> {
}
