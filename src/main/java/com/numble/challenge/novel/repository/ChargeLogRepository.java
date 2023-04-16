package com.numble.challenge.novel.repository;

import com.numble.challenge.novel.domain.ChargeLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargeLogRepository extends JpaRepository<ChargeLog, Long> {
}
