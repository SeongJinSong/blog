package com.study.openapi.redis.domain;

import org.springframework.data.redis.core.ZSetOperations;

import javax.annotation.Resource;

public class QueryScoreCache {
    @Resource(name = "redisTemplate")
    private ZSetOperations<String, String> zSetOperations;
    public void addScore(String key, String value, double score){
        /*TODO
           1. key는 랭킹할 키, value는 검색조건, score는 카운트로 하자
           REDIS는 싱글 스레드이기 때문에 Atomic하여 Race Condition을 피할 수 있다.(그래도 잘못짜면 발생한다.)
         */
        zSetOperations.add(key, value, score);
    }
    //Lock aside Cache와 Write Back을 사용해야 한다.
}
