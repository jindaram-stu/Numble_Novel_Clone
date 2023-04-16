package com.numble.challenge.novel.service;

import com.numble.challenge.novel.domain.ChargeLog;
import com.numble.challenge.novel.domain.User;
import com.numble.challenge.novel.repository.ChargeLogRepository;
import com.numble.challenge.novel.repository.RedisDistributedLockRepository;
import com.numble.challenge.novel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointService {

    private final UserRepository userRepository;
    private final ChargeLogRepository chargeLogRepository;
    private final RedisDistributedLockRepository redisLockRepository;
    @Transactional
    public void chargePoint(User user, Long point) {
        if (!redisLockRepository.lock(user.getId(), "CHARGE_POINT")) {
            throw new IllegalArgumentException("이미 충전이 진행중입니다.");
        }
        user.chargePoint(point);
        userRepository.save(user);
        chargeLogRepository.save(createChargeLog(user, point));
    }

    private ChargeLog createChargeLog(User user, Long point) {
        return ChargeLog.builder()
                .user(user)
                .point(point)
                .build();
    }

    @PostConstruct
    public void init() {

    }

}
