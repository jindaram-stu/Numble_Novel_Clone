package com.numble.challenge.novel.api;

import com.numble.challenge.novel.api.dto.request.NovelPaymentRequest;
import com.numble.challenge.novel.config.security.AbstractUser;
import com.numble.challenge.novel.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    // 소설 구매
    @PostMapping("/pay")
    public ResponseEntity<NovelPaymentRequest> paymentNovel(@RequestBody NovelPaymentRequest novelPaymentRequest,
                             @AuthenticationPrincipal AbstractUser abstractUser) {
        paymentService.paymentNovel(novelPaymentRequest, abstractUser.getUser());
        return new ResponseEntity<>(novelPaymentRequest, HttpStatus.OK);
    }

}
