package com.numble.challenge.novel.api.dto.response;

import com.numble.challenge.novel.domain.FreeType;
import com.numble.challenge.novel.domain.Genre;
import com.numble.challenge.novel.domain.NovelEpisode;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class NovelResponse {

    private Long id;
    private String title;

    private String author;

    private String description;

    private FreeType freeType;

    private Genre genre;

    private List<NovelEpisodeSlimResponse> novelEpisodeSlimResponses;
}
