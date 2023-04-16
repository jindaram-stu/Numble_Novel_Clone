package com.numble.challenge.novel.api;

import com.numble.challenge.novel.api.dto.request.UserCreateRequest;
import com.numble.challenge.novel.api.dto.request.UserLoginRequest;
import com.numble.challenge.novel.config.security.AbstractUser;
import com.numble.challenge.novel.domain.User;
import com.numble.challenge.novel.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/auth/signUp")
    public ResponseEntity signuUp(@RequestBody UserCreateRequest userCreateRequest) {
        userService.signUp(userCreateRequest);
        return ResponseEntity.ok("성공적으로 회원가입 하였습니다.");
    }

    @PostMapping("/auth/login")
    public ResponseEntity login(@RequestBody UserLoginRequest userLoginRequest) {
        return ResponseEntity.ok(userService.login(userLoginRequest));
    }


}
