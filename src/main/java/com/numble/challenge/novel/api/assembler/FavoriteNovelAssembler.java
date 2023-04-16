package com.numble.challenge.novel.api.assembler;

import com.numble.challenge.novel.api.dto.response.FavoriteNovelResponse;
import com.numble.challenge.novel.domain.FavoriteNovel;
import com.numble.challenge.novel.domain.LastReadedDetail;

public class FavoriteNovelAssembler {

    public static FavoriteNovelResponse toDto(FavoriteNovel favoriteNovel, LastReadedDetail detail) {
        return FavoriteNovelResponse.builder()
                .novel(NovelAssembler.toSlimDto(favoriteNovel.getNovel()))
                .episode(detail.getEpisode())
                .page(detail.getPage())
                .build();
    }
}
