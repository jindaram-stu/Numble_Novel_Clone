package com.numble.challenge.novel.service;

import com.numble.challenge.novel.api.assembler.FavoriteNovelAssembler;
import com.numble.challenge.novel.api.dto.response.FavoriteNovelResponse;
import com.numble.challenge.novel.domain.FavoriteNovel;
import com.numble.challenge.novel.domain.LastReadedDetail;
import com.numble.challenge.novel.domain.User;
import com.numble.challenge.novel.repository.FavoriteNovelRepository;
import com.numble.challenge.novel.repository.NovelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class FavoriteNovelService {

    private final StringRedisTemplate stringRedisTemplate;
    private final NovelRepository novelRepository;
    private final FavoriteNovelRepository favoriteNovelRepository;

    public void register(User user, Long novelId) {
        favoriteNovelRepository.save(createFavoriteNovel(user, novelId));
    }

    private FavoriteNovel createFavoriteNovel(User user, Long novelId) {
        return FavoriteNovel.builder()
                .user(user)
                .novel(novelRepository.getReferenceById(novelId))
                .build();
    }

    @Transactional(readOnly = true)
    public List<FavoriteNovelResponse> readFavoriteNovel(Long userId) {
        List<FavoriteNovel> favoriteNovelList = favoriteNovelRepository.findByUserId(userId);
        String key = userId + "::lastReadedPage";
        Map<Object, Object> userLastReadedHash = stringRedisTemplate.opsForHash().entries(key);
        List<FavoriteNovelResponse> responses = favoriteNovelList.stream()
                .map(favoriteNovel -> FavoriteNovelAssembler.toDto(favoriteNovel,
                        createLastReadedDetail(getRawLastReadedDetail(userLastReadedHash, favoriteNovel.getNovel().getId()))))
                .collect(Collectors.toList());
        return responses;
    }

    public String getRawLastReadedDetail(Map<Object, Object> lastReaded, Long novelId) {
        return lastReaded.get(novelId.toString()).toString();
    }

    public LastReadedDetail createLastReadedDetail(String rawText) {
        String[] split = rawText.split(":");
        return LastReadedDetail.builder()
                .page(Long.valueOf(split[1]))
                .episode(Long.valueOf(split[0]))
                .build();
    }

}
