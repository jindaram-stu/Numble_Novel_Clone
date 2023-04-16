package com.numble.challenge.novel.exception;

public enum Problem {

    USER_NOT_FOUND(400, "유저를 찾을 수 없습니다."),
    NOVEL_NOT_FOUND(400,"소설을 찾을 수 없습니다."),
    SHORT_OF_POINTS(400, "구매하기 위한 포인트가 부족합니다.");

    private int errorCode;
    private String errorMessage;

    Problem(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
