package com.numble.challenge.novel.config.security;

import com.numble.challenge.novel.domain.User;

public interface AbstractUser {


    User getUser();

    boolean isLoginUser();

}
