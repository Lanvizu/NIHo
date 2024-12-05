package com.study.NIHo.api.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

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
