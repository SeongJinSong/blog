package com.study.openapi.redis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
@Slf4j
public class EmbeddedRedisConfig {
    @Value("${spring.redis.database.port}")
    private int redisPort;
    @Value("${spring.redis.embedded.maxmemory}")
    private String maxMemory;

    private RedisServer redisServer;

    public EmbeddedRedisConfig() {
        log.warn("Embedded Redis Constructor");
    }

    @Bean
    public RedisServer redisServer() {
        return redisServer;
    }

    @PostConstruct
    public void startServer() {
        redisServer = RedisServer.builder()
                .port(redisPort)
                .setting("maxmemory " + maxMemory)
                .build();
        try {
            redisServer.start();
        } catch (Exception e) {
//            log.error(e.getMessage());
        }
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null && redisServer.isActive()) {
            redisServer.stop();
        }
    }
}
