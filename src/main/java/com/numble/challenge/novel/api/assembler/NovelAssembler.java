package com.numble.challenge.novel.api.assembler;

import com.numble.challenge.novel.api.dto.response.NovelResponse;
import com.numble.challenge.novel.api.dto.response.NovelSlimResponse;
import com.numble.challenge.novel.domain.Novel;

public class NovelAssembler {

    public static NovelSlimResponse toSlimDto(Novel novel) {
        return NovelSlimResponse.builder()
                .id(novel.getId())
                .title(novel.getTitle())
                .author(novel.getAuthor())
                .description(novel.getDescription())
                .genre(novel.getGenre())
                .freeType(novel.getFreeType())
                .build();
    }


}
