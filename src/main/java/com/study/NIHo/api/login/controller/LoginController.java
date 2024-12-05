package com.study.NIHo.api.login.controller;

import com.study.NIHo.api.common.response.entity.ApiResponseEntity;
import com.study.NIHo.api.login.application.LoginService;
import com.study.NIHo.api.login.dto.request.LoginRequestDTO;
import com.study.NIHo.api.login.dto.response.LoginResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponseEntity> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        // login 체크 후 token 생성
        LoginResponseDTO loginInfo = loginService.login(loginRequestDTO);

        // HttpOnly 쿠키 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, loginInfo.accessTokenCookie().toString());

        return ApiResponseEntity.successResponseEntityWithHeaders(loginInfo, headers);
    }
}
