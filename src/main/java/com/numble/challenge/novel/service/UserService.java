package com.numble.challenge.novel.service;

import com.numble.challenge.novel.api.dto.request.UserCreateRequest;
import com.numble.challenge.novel.api.dto.request.UserLoginRequest;
import com.numble.challenge.novel.domain.User;
import com.numble.challenge.novel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void signUp(UserCreateRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 유저 이메일 입니다.");
        }

        userRepository.save(createUser(request));
    }

    public Long login(UserLoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일이 틀렸습니다."));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        return user.getId();
    }

    private User createUser(UserCreateRequest userCreateRequest) {
        return User.builder()
                .email(userCreateRequest.getEmail())
                .password(passwordEncoder.encode(userCreateRequest.getPassword()))
                .nickname(userCreateRequest.getNickname())
                .build();
    }

}
