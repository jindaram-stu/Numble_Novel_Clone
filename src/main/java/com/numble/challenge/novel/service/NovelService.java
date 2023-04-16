package com.numble.challenge.novel.service;

import com.numble.challenge.novel.api.assembler.NovelEpisodeAssembler;
import com.numble.challenge.novel.api.dto.request.NovelContentCreateRequest;
import com.numble.challenge.novel.api.dto.request.NovelCreateRequest;
import com.numble.challenge.novel.api.dto.request.NovelEpisodeCreateRequest;
import com.numble.challenge.novel.api.dto.response.NovelEpisodeResponse;
import com.numble.challenge.novel.api.dto.response.NovelResponse;
import com.numble.challenge.novel.config.security.AbstractUser;
import com.numble.challenge.novel.domain.Novel;
import com.numble.challenge.novel.domain.NovelContent;
import com.numble.challenge.novel.domain.NovelEpisode;
import com.numble.challenge.novel.domain.User;
import com.numble.challenge.novel.exception.NovelNotFoundException;
import com.numble.challenge.novel.exception.UserNotFoundException;
import com.numble.challenge.novel.repository.NovelEpisodeRepository;
import com.numble.challenge.novel.repository.NovelRepository;
import com.numble.challenge.novel.repository.RedisHitRepository;
import com.numble.challenge.novel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class NovelService {

    private final NovelRepository novelRepository;
    private final NovelEpisodeRepository novelEpisodeRepository;
    private final PaymentService paymentService;

    private final UserRepository userRepository;
    private final RedisHitRepository redisHitRepository;

    @Transactional
    public Long createNovel(NovelCreateRequest ncr) {
        Novel novel = Novel.builder()
                .title(ncr.getTitle())
                .author("테스트 닉네임")
                .description(ncr.getDescription())
                .genre(ncr.getGenre())
                .freeType(ncr.getFreeType())
                .build();

        return novelRepository.save(novel).getId();
    }

    public NovelEpisodeResponse readNovelEpisode(Long novelId, Long episodeId, AbstractUser abstractUser) {
        NovelEpisode episode = novelEpisodeRepository.findByNovelEpisodeId(novelId, episodeId)
                .orElseThrow(() -> new NovelNotFoundException("해당 소설을 찾을 수 없습니다."));

        if (episode.getPrice().equals(0L) && !abstractUser.isLoginUser()) {
            return NovelEpisodeAssembler.toDto(episode, null);
        }

        User user = userRepository.findByEmail(abstractUser.getUser().getEmail())
                .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));
        if (episode.getPrice().equals(0L) && abstractUser.isLoginUser()) {
            redisHitRepository.increamentHit(episodeId);
            return NovelEpisodeAssembler.toDto(episode, user.isFavorite(novelId));
        }

        if (!paymentService.validatePaymentLog(user, episode)) {
            throw new IllegalArgumentException("결제 후에 열람할 수 있습니다.");
        }
        redisHitRepository.increamentHit(episodeId);
        return NovelEpisodeAssembler.toDto(episode, user.isFavorite(novelId));
    }

    @Transactional
    public void createNovelEpisode(NovelEpisodeCreateRequest necr) {
        Novel findNovel = novelRepository.getReferenceById(necr.getNovelId());
        Long sequence = novelEpisodeRepository.countNovelEpisodeByNovel(findNovel) + 1L;
        NovelEpisode ep = NovelEpisode.builder()
                .novel(findNovel)
                .title(necr.getTitle())
                .price(necr.getPrice())
                .sequence(sequence)
                .build();
        ep.addContent(createNovelContents(necr.getContents()));

        novelEpisodeRepository.save(ep);
    }

    private List<NovelContent> createNovelContents(List<NovelContentCreateRequest> nccrs) {
        AtomicLong page = new AtomicLong(0);
        return nccrs.stream()
                .map(nccr -> createNovelContent(nccr, page.getAndIncrement()))
                .collect(Collectors.toList());
    }

    private NovelContent createNovelContent(NovelContentCreateRequest novelContentCreateRequest,
                                            Long page) {
        return NovelContent.builder()
                .content(novelContentCreateRequest.getContent())
                .page(page)
                .build();
    }

    @Scheduled(cron = "59 * * * * *")
    @Transactional
    public void renewHits() {
        Map<String, String> hitEntries = redisHitRepository.getEntries();
        log.info("{}", hitEntries.size());
        hitEntries.keySet().stream()
                .forEach(key -> {

                    novelEpisodeRepository.bulkHit(Long.valueOf(hitEntries.get(key)), keyToLong(key.toString()));
                });
    }

    public Long keyToLong(String key) {
        return Long.valueOf(key.split(":")[0]);
    }


}
