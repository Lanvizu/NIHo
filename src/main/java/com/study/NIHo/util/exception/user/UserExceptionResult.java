package com.study.NIHo.util.exception.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserExceptionResult {

    NOT_EXISTS(HttpStatus.BAD_REQUEST, "U0001", "존재하지 않는 사용자입니다."),
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "U0002", "이미 사용 중인 이메일입니다."),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "U0003", "패스워드 불일치");

    private final HttpStatus status;
    private final String code;
    private final String message;
}