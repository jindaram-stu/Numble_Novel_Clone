package com.numble.challenge.novel.api.dto.response;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoriteNovelResponse {

    private NovelSlimResponse novel;

    private Long episode;

    private Long page;

}
