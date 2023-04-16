package com.numble.challenge.novel.api.dto.response;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class NovelEpisodeSlimResponse {

    private String episodeTitle;

    private Long sequence;

    private Long hit;

    private Long price;

    private Instant createDate;

    private Boolean isFavorite;

}
