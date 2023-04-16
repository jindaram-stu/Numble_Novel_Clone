package com.numble.challenge.novel.api.dto.response;

import com.numble.challenge.novel.domain.FreeType;
import com.numble.challenge.novel.domain.Genre;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NovelSlimResponse {

    private Long id;
    private String title;

    private String author;

    private String description;

    private FreeType freeType;

    private Genre genre;
}
