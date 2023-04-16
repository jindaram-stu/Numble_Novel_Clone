package com.numble.challenge.novel.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisHitRepository {

    private final StringRedisTemplate redisTemplate;

    private final String key = "EPISODE:HITS";

    public void increamentHit(Long episodeId) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        String hashKey = episodeId + ":hits";

        hashOperations.increment(key, hashKey, 1);
    }

    public Map<String, String> getEntries() {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        return hashOperations.entries(key);
    }


}
