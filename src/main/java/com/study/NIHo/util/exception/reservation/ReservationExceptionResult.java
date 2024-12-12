package com.study.NIHo.util.exception.reservation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReservationExceptionResult {
    NOT_EXISTS(HttpStatus.BAD_REQUEST, "B0001", "존재하지 않는 예약입니다."),
    ALREADY_RESERVED(HttpStatus.BAD_REQUEST, "B0002", "이미 해당 날짜에 예약이 존재합니다."),
    INVALID_DATE_RANGE(HttpStatus.BAD_REQUEST, "B0003", "체크인 날짜는 체크아웃 날짜보다 이후일 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
