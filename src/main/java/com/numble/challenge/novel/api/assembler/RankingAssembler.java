package com.numble.challenge.novel.api.assembler;

import com.numble.challenge.novel.api.dto.response.RankingResponse;
import com.numble.challenge.novel.domain.Novel;

public class RankingAssembler {

    public static RankingResponse toDto(Novel novel) {
        return RankingResponse.builder()
                .id(novel.getId())
                .author(novel.getAuthor())
                .title(novel.getTitle())
                .description(novel.getDescription())
                .genre(novel.getGenre())
                .build();
    }

}
