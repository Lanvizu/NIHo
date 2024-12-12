package com.study.NIHo.api.reservation.enums;

import lombok.Getter;

@Getter
public enum ReservationStatus {
    RESERVED("RESERVED", "예약 중"),
    COMPLETED("COMPLETED", "예약 종료"),
    CANCELLED("CANCELLED", "예약 취소");

    private final String code;
    private final String statusInfo;

    ReservationStatus(String code, String statusInfo) {
        this.code = code;
        this.statusInfo = statusInfo;
    }
}
