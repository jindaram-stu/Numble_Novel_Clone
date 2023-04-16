package com.numble.challenge.novel.repository.util;

import com.numble.challenge.novel.api.dto.request.NovelContentCreateRequest;
import com.numble.challenge.novel.api.dto.request.NovelCreateRequest;
import com.numble.challenge.novel.api.dto.request.NovelEpisodeCreateRequest;
import com.numble.challenge.novel.domain.FreeType;
import com.numble.challenge.novel.domain.Genre;
import com.numble.challenge.novel.repository.NovelRepository;
import com.numble.challenge.novel.service.NovelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SampleDataGenerator {

    private final NovelRepository novelRepository;
    private final NovelService novelService;

//    @PostConstruct
    public void init() {

        for (int i=0; i<10000; i++) {
            if(i % 2 == 0) {
                NovelCreateRequest ncr2 = new NovelCreateRequest("테스트" + i, "a", Genre.FANTANSY, FreeType.NON_FREE);
                Long novelId = novelService.createNovel(ncr2);
                createNovelEpisode(novelId);
                continue;
            }
            NovelCreateRequest ncr = new NovelCreateRequest("테스트" + i, "a", Genre.FANTANSY, FreeType.FREE);
            Long novelId = novelService.createNovel(ncr);
            createNovelEpisode(novelId);
        }

    }

    public void createNovelEpisode(Long novelId) {
        for (int i=0; i<100; i++) {
            NovelEpisodeCreateRequest necr = new NovelEpisodeCreateRequest(novelId, "테스트" + i + "에피소드", 0L, createNovelContents());
            novelService.createNovelEpisode(necr);
        }
    }

    public List<NovelContentCreateRequest> createNovelContents() {
        List<NovelContentCreateRequest> requests = new ArrayList<>();
        for (int i=0; i<1; i++) {
            requests.add(new NovelContentCreateRequest("내용 1"));
        }        return requests;
    }
}
