package com.numble.challenge.novel.api.assembler;

import com.numble.challenge.novel.api.dto.response.NovelContentSlimRepsonse;
import com.numble.challenge.novel.domain.NovelContent;

import java.util.List;
import java.util.stream.Collectors;

public class NovelContentAssembler {

    public static NovelContentSlimRepsonse toSlimDto(NovelContent novelContent) {
        return NovelContentSlimRepsonse.builder()
                .id(novelContent.getId())
                .page(novelContent.getPage())
                .content(novelContent.getContent())
                .build();
    }

    public static List<NovelContentSlimRepsonse> toSlimDtos(List<NovelContent> novelContents) {
        return novelContents.stream()
                .map(content -> toSlimDto(content))
                .collect(Collectors.toList());
    }
}
