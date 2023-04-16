package com.numble.challenge.novel.api.assembler;

import com.numble.challenge.novel.api.dto.response.NovelEpisodeResponse;
import com.numble.challenge.novel.domain.NovelEpisode;
public class NovelEpisodeAssembler {

    public static NovelEpisodeResponse toDto(NovelEpisode novelEpisode, Boolean isFavorite) {
        return NovelEpisodeResponse.builder()
                .episodeTitle(novelEpisode.getTitle())
                .sequence(novelEpisode.getSequence())
                .hit(novelEpisode.getHit())
                .price(novelEpisode.getPrice())
                .createDate(novelEpisode.getCreateDate())
                .isFavorite(isFavorite)
                .novel(NovelAssembler.toSlimDto(novelEpisode.getNovel()))
                .contents(NovelContentAssembler.toSlimDtos(novelEpisode.getContents()))
                .build();
    }

}
