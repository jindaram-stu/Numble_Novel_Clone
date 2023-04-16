package com.numble.challenge.novel.api;

import com.numble.challenge.novel.api.dto.response.RankingResponse;
import com.numble.challenge.novel.common.LogExecutionTime;
import com.numble.challenge.novel.service.RankingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class RankingController {

    private final RankingService rankingService;

    @LogExecutionTime
    @GetMapping("/ranking/all")
    public List<RankingResponse> allRanking() {
        return rankingService.loadAllNovelRanking();
    }

    @GetMapping("/ranking/free")
    public List<RankingResponse> freeRanking() {
        return rankingService.loadFreeNovelRanking();
    }

    @GetMapping("/ranking/non-free")

    public List<RankingResponse> nonFreeRanking() {
        return rankingService.loadNonFreeNovelRanking();
    }

}
