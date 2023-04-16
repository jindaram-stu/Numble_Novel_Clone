package com.numble.challenge.novel.config.security;

import com.numble.challenge.novel.domain.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginUser implements AbstractUser {

    private User loginUser;

    @Override
    public User getUser() {
        return loginUser;
    }

    @Override
    public boolean isLoginUser() {
        return true;
    }
}
