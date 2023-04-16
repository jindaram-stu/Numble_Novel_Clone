package com.numble.challenge.novel.api;

import com.numble.challenge.novel.api.dto.request.LastReadedPageRequest;
import com.numble.challenge.novel.api.dto.request.NovelCreateRequest;
import com.numble.challenge.novel.api.dto.request.NovelEpisodeCreateRequest;
import com.numble.challenge.novel.api.dto.response.FavoriteNovelResponse;
import com.numble.challenge.novel.api.dto.response.NovelEpisodeResponse;
import com.numble.challenge.novel.common.LogExecutionTime;
import com.numble.challenge.novel.common.SuccessResponse;
import com.numble.challenge.novel.config.security.AbstractUser;
import com.numble.challenge.novel.domain.Novel;
import com.numble.challenge.novel.repository.NovelRepository;
import com.numble.challenge.novel.service.FavoriteNovelService;
import com.numble.challenge.novel.service.NovelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class NovelController {

    private final NovelRepository novelRepository;
    private final NovelService novelService;
    private final FavoriteNovelService favoriteNovelService;
    private final StringRedisTemplate stringRedisTemplate;

    // 소설 조회
    @GetMapping("/novel")
    public Novel readNovelEpisodeList(@RequestParam Long novelId) {
        return novelRepository.findNovelWithContent(novelId);
    }
    
    // 소설 에피소드 조회
    @GetMapping("/novel/episode")
    public NovelEpisodeResponse readEpisode(@RequestParam Long novelId,
                                            @RequestParam Long episodeId,
                                            @AuthenticationPrincipal AbstractUser abstractUser) {
        NovelEpisodeResponse episode = novelService.readNovelEpisode(novelId, episodeId, abstractUser);
        return episode;
    }

    // 소설 생성
    @PostMapping("/novel")
    public ResponseEntity<NovelCreateRequest> createNovel(@RequestBody NovelCreateRequest ncr) {
        novelService.createNovel(ncr);
        return new ResponseEntity<>(ncr, HttpStatus.OK);
    }

    // 소설 에피소드 생성
    @PostMapping("/novel/episode")
    public ResponseEntity<NovelEpisodeCreateRequest> createNovelEpisode(@RequestBody NovelEpisodeCreateRequest necr) {
        novelService.createNovelEpisode(necr);
        return new ResponseEntity<>(necr, HttpStatus.OK);
    }

    // 선호작 등록
    @PostMapping("/novel/favorite")
    public ResponseEntity<NovelSlimResponse> createFavoriteNovel(@AuthenticationPrincipal AbstractUser abstractUser,
                                    @RequestParam Long novelId) {
        favoriteNovelService.register(abstractUser.getUser(), novelId);
        return new ResponseEntity<>(new SuccessResponse("성공적으로 선호작을 등록했습니다."), HttpStatus.OK);
    }
    
    // 마지막 읽은 페이지 기록
    @PostMapping("/novel/lastReadedPage")
    public ResponseEntity<LastReadedPageRequest> recordLastReadPage(@AuthenticationPrincipal AbstractUser abstractUser,
                                   @RequestBody LastReadedPageRequest request) {
        String key = abstractUser.getUser().getId() + "::lastReadedPage";
        stringRedisTemplate.opsForHash().put(key,
                                             request.getNovelId().toString(),
                                       request.getNovelEpisodeId() + ":" + request.getPage());
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @GetMapping("/novel/lastReadedPage")
    public List<FavoriteNovelResponse> readFavoriteNovels(@AuthenticationPrincipal AbstractUser abstractUser) {
        List<FavoriteNovelResponse> responses = favoriteNovelService.readFavoriteNovel(abstractUser.getUser().getId());
        return responses;
    }

}
