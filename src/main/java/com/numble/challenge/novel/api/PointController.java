package com.numble.challenge.novel.api;

import com.numble.challenge.novel.api.dto.request.PointChargeRequest;
import com.numble.challenge.novel.config.security.AbstractUser;
import com.numble.challenge.novel.domain.User;
import com.numble.challenge.novel.repository.UserRepository;
import com.numble.challenge.novel.service.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class PointController {

    private final PointService pointService;
    private final UserRepository userRepository;

    // 포인트 충전
    @PostMapping("/point")
    public ResponseEntit<y<PointChargeRequest> chargePoint(@RequestBody PointChargeRequest pointChargeRequest,
                                      @AuthenticationPrincipal AbstractUser abstractUser) {
        pointService.chargePoint(abstractUser.getUser(), pointChargeRequest.getChargePoint());
        return new ResponseEntity<>(pointChargeRequest, HttpStatus.OK);
    }

}
