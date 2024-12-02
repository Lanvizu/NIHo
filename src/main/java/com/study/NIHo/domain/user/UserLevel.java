package com.study.NIHo.domain.user;

import lombok.Getter;

@Getter
public enum UserLevel {
    USER("USER", "가입된 회원"),
    ADMIN("ADMIN", "관리자");

    private final String code;
    private final String levelInfo;

    UserLevel(String code, String levelInfo) {
        this.code = code;
        this.levelInfo = levelInfo;
    }
}
