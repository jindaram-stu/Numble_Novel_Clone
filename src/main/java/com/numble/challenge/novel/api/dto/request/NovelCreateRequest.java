package com.numble.challenge.novel.api.dto.request;

import com.numble.challenge.novel.domain.FreeType;
import com.numble.challenge.novel.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NovelCreateRequest {

    private String title;
    private String description;
    private Genre genre;

    private FreeType freeType;


}
