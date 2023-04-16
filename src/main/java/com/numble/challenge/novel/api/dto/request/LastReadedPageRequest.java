package com.numble.challenge.novel.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LastReadedPageRequest {

    private Long novelId;

    private Long novelEpisodeId;

    private Long page;

}
