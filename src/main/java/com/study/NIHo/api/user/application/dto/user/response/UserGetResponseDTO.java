package com.study.NIHo.api.user.application.dto.user.response;

import com.study.NIHo.domain.user.User;
import com.study.NIHo.domain.user.UserRole;
import com.study.NIHo.domain.user.UserStatus;
import lombok.Builder;

@Builder
public record UserGetResponseDTO(Long userid, String email, String username, String password, UserRole userRole, UserStatus userStatus) {

    public static UserGetResponseDTO of(User user) {
        return UserGetResponseDTO.builder()
                .userid(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .userRole(user.getUserRole())
                .userStatus(user.getUserStatus())
                .build();
    }
}
