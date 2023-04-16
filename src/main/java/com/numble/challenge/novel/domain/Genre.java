package com.numble.challenge.novel.domain;

public enum Genre {
    ROMANCE("로맨스"),
    ROMANCE_FANTASY("로맨스 판타지"),
    FANTANSY("판타지"),
    CONTEMPORARY_FATASY("현대 판타지"),
    MARTIAL("무협"),
    MYSTERY("미스테리"),
    LIGHT_NOVEL("라이트 노벨"),
    FREE("자유");

    private String name;

    Genre(String name) {
        this.name = name;
    }
}
