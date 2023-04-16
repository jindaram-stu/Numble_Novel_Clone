package com.numble.challenge.novel.api.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NovelContentSlimRepsonse {

    private Long id;

    private Long page;

    private String content;
}
