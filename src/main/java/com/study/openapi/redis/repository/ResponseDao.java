package com.study.openapi.redis.repository;

public interface ResponseDao {
    void addItem(Object response, String key);
    Object findById(String key);
}
