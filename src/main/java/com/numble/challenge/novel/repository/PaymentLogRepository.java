package com.numble.challenge.novel.repository;

import com.numble.challenge.novel.domain.NovelEpisode;
import com.numble.challenge.novel.domain.PaymentLog;
import com.numble.challenge.novel.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentLogRepository extends JpaRepository<PaymentLog, Long> {

    Optional<PaymentLog> findByUserAndNovelEpisode(User user, NovelEpisode novelEpisode);

}
