package com.study.NIHo.domain.user.enums;

import lombok.Getter;

@Getter
public enum UserStatus {
    BAN("BAN", "밴 처리된 회원"),
    NORMAL("NORMAL", "회원 상태 양호");

    private final String code;
    private final String statusInfo;

    UserStatus(String code, String statusInfo) {
        this.code = code;
        this.statusInfo = statusInfo;
    }
}
