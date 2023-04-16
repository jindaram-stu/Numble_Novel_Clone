package com.numble.challenge.novel.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LastReadedDetail {

    private Long episode;

    private Long page;

}
