package com.study.NIHo.util.exception.room;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum RoomExceptionResult {

    NOT_EXISTS(HttpStatus.BAD_REQUEST, "R0001", "존재하지 않는 객실 아이디입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
