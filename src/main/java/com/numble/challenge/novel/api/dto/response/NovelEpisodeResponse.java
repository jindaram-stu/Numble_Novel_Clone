package com.numble.challenge.novel.api.dto.response;


import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class NovelEpisodeResponse {

    private NovelSlimResponse novel;

    private String episodeTitle;

    private Long sequence;

    private Long hit;

    private Long price;

    private Instant createDate;

    private Boolean isFavorite;

    private List<NovelContentSlimRepsonse> contents;

}
