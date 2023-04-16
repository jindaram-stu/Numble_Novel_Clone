package com.numble.challenge.novel.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RedisDistributedLockRepository {

    private final StringRedisTemplate redisTemplate;

    public boolean lock(Long userId, String behavior) {
        return redisTemplate
            .opsForValue()
                .setIfAbsent(buildLockKey(userId, behavior), "lock", Duration.ofMillis(2_000));
    }

    private String buildLockKey(Long primary, String behavior) {
        return String.valueOf(primary) + ":" + behavior;
    }


}
