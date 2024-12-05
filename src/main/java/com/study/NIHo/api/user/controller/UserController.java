package com.study.NIHo.api.user.controller;

import com.study.NIHo.api.common.response.entity.ApiResponseEntity;
import com.study.NIHo.api.user.application.UserDeleteService;
import com.study.NIHo.api.user.application.UserGetService;
import com.study.NIHo.util.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserGetService userGetService;

    private final UserDeleteService userDelService;

    @GetMapping("/user")
    public ResponseEntity<ApiResponseEntity> dashboard(Authentication authentication) {
        // 사용자 정보 조회
        var result = userGetService.getUserById(JwtUtil.getLoginId(authentication));

        return ApiResponseEntity.successResponseEntity(result);
    }

    @DeleteMapping("/user")
    public ResponseEntity<ApiResponseEntity> delete(Authentication authentication) {
        // 사용자 정보 삭제
        userDelService.deleteUser(JwtUtil.getLoginId(authentication));

        return ApiResponseEntity.successResponseEntity();
    }
}
