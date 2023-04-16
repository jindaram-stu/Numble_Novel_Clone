package com.numble.challenge.novel.api.dto.request;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
@AllArgsConstructor
public class NovelPaymentRequest {

    private Long novelId;

    private Long episodeId;

}
