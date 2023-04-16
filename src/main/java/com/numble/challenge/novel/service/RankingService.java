package com.numble.challenge.novel.service;

import com.numble.challenge.novel.api.dto.response.RankingResponse;
import com.numble.challenge.novel.config.cache.CacheKey;
import com.numble.challenge.novel.domain.FreeType;
import com.numble.challenge.novel.repository.NovelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RankingService {

    private final int RANKING_LIMIT = 5;
    private final NovelRepository novelRepository;

    @Cacheable(value = CacheKey.CACHE_ALL_RANKING, cacheManager = "redisCacheManager")
    public List<RankingResponse> loadAllNovelRanking() {
        List<RankingResponse> rawNovels = novelRepository.findByAllOrderByHit(PageRequest.of(0,10000));
        return sort(rawNovels);
    }

    public List<RankingResponse> loadAllNovelRankingNoCache() {
        List<RankingResponse> rawNovels = novelRepository.findByAllOrderByHit(PageRequest.of(0, 10000));
        return sort(rawNovels);
    }

    @Cacheable(value = CacheKey.CACHE_FREE_RANKING, cacheManager = "redisCacheManager")
    public List<RankingResponse> loadFreeNovelRanking() {
        List<RankingResponse> rawNovels = novelRepository.findByFreeTypeOrderByHit(FreeType.FREE, PageRequest.of(0,RANKING_LIMIT));
        return sort(rawNovels);
    }

    @Cacheable(value = CacheKey.CACHE_NON_FREE_RANKING, cacheManager = "redisCacheManager")
    public List<RankingResponse> loadNonFreeNovelRanking() {
        List<RankingResponse> rawNovels = novelRepository.findByFreeTypeOrderByHit(FreeType.NON_FREE, PageRequest.of(0,RANKING_LIMIT));
        return sort(rawNovels);
    }

    public List<RankingResponse> sort(List<RankingResponse> rankingResponses) {
        return rankingResponses.stream()
                .sorted(Comparator.comparing(RankingResponse::getTotalHits).reversed())
                .collect(Collectors.toList());
    }

}
