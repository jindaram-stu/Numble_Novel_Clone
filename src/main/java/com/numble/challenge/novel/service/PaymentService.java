package com.numble.challenge.novel.service;


import com.numble.challenge.novel.api.dto.request.NovelPaymentRequest;
import com.numble.challenge.novel.domain.NovelEpisode;
import com.numble.challenge.novel.domain.PaymentLog;
import com.numble.challenge.novel.domain.User;
import com.numble.challenge.novel.exception.NovelNotFoundException;
import com.numble.challenge.novel.exception.ShortOfPointException;
import com.numble.challenge.novel.exception.UserNotFoundException;
import com.numble.challenge.novel.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final NovelRepository novelRepository;
    private final NovelEpisodeRepository novelEpisodeRepository;
    private final PaymentLogRepository paymentLogRepository;
    private final UserRepository userRepository;

    private final RedisDistributedLockRepository redisLockRepository;

    @Transactional
    public void paymentNovel(NovelPaymentRequest request, User loginUser) {
        if (!redisLockRepository.lock(loginUser.getId(), "PAYMENT_NOVEL")) {
            throw new IllegalArgumentException("이미 구매가 진행중입니다.");
        }
        User user = userRepository.findById(loginUser.getId())
                .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));

        validateAlreadyPayment(loginUser, novelEpisodeRepository.getReferenceById(request.getEpisodeId()));

        NovelEpisode episode = novelEpisodeRepository.findByNovelEpisodeId(request.getNovelId(), request.getEpisodeId())
                .orElseThrow(() -> new NovelNotFoundException("해당 소설을 찾을 수 없습니다."));;
        Long price = episode.getPrice();

        if (user.getPoint() < price) {
            throw new ShortOfPointException("포인트가 부족합니다.");
        }
        user.payment(price);
        paymentLogRepository.save(createPaymentLog(request, user));
    }

    private void validateAlreadyPayment(User user, NovelEpisode novelEpisode) {
        if (validatePaymentLog(user, novelEpisode)) {
            throw new IllegalArgumentException("이미 구매한 소설입니다.");
        }
    }
    public boolean validatePaymentLog(User user, NovelEpisode novelEpisode) {
        return paymentLogRepository.findByUserAndNovelEpisode(user, novelEpisode)
                .isPresent();
    }

    public PaymentLog createPaymentLog(NovelPaymentRequest request, User user) {
        return PaymentLog.builder()
                .user(user)
                .novel(novelRepository.getReferenceById(request.getNovelId()))
                .novelEpisode(novelEpisodeRepository.getReferenceById(request.getEpisodeId()))
                .build();
    }

}
