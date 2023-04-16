package com.numble.challenge.novel.api.dto.request;

import lombok.*;

@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
public class NovelContentCreateRequest {

    private String content;

}
