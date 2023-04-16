package com.numble.challenge.novel.service;

import com.numble.challenge.novel.domain.User;
import com.numble.challenge.novel.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class PointServiceTest {

    private final int THREAD_COUNT = 100;

    @Autowired
    private PointService pointService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void 동시성_테스트() throws InterruptedException {
        User user = userRepository.findByEmail("jinsb1999@naver.com").get();
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

        for(int i=0; i < THREAD_COUNT; i++) {
            executorService.execute(() -> {
                try {
                    pointService.chargePoint(user, 100L);
                } catch (ObjectOptimisticLockingFailureException e) {
                    throw e;

                }
                latch.countDown();
            });
        }
        latch.await();
        System.out.println(user.getPoint());
        log.info("테스트 종료");
        assertThat(user.getPoint()).isEqualTo(100L);
    }

}