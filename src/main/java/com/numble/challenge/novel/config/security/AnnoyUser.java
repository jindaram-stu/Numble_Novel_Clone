package com.numble.challenge.novel.config.security;

import com.numble.challenge.novel.domain.User;

public class AnnoyUser implements AbstractUser {

    @Override
    public User getUser() {
        throw new IllegalArgumentException("로그인이 필요한 접근입니다.");
    }

    @Override
    public boolean isLoginUser() {
        return false;
    }
}
