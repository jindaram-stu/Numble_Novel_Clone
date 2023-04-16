package com.numble.challenge.novel.api.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
public class NovelEpisodeCreateRequest {

    private Long novelId;

    private String title;
    private Long price;

    private List<NovelContentCreateRequest> contents;

}
