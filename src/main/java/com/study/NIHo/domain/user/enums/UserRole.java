package com.study.NIHo.domain.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    USER("USER"),
    ADMIN("ADMIN");

    private final String role;

    public static UserRole fromUserRole(String role) {
        for(UserRole userRole : UserRole.values()) {
            if(userRole.getRole().equalsIgnoreCase(role)) {
                return userRole;
            }
        }

        throw new IllegalArgumentException("No Role Found!");
    }
}
