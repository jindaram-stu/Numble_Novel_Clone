package com.numble.challenge.novel.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserCreateRequest {

    private String email;
    private String password;

    private String nickname;

}
