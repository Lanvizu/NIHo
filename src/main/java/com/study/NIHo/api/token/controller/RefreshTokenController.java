package com.study.NIHo.api.token.controller;

import com.study.NIHo.api.common.response.entity.ApiResponseEntity;
import com.study.NIHo.api.token.application.RefreshTokenService;
import com.study.NIHo.api.token.dto.request.RefreshTokenRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    @PostMapping("/token-refresh")
    public ResponseEntity<ApiResponseEntity> tokenRefresh(@RequestBody @Valid RefreshTokenRequestDTO refreshTokenRequestDTO) {
        // token 재발급
        var result = refreshTokenService.refreshToken(refreshTokenRequestDTO.getRefreshToken());

        return ApiResponseEntity.successResponseEntity(result);
    }

}