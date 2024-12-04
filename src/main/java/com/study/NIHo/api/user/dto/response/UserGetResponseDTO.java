package com.study.NIHo.api.user.dto.response;

import com.study.NIHo.domain.user.User;
import com.study.NIHo.domain.user.enums.UserRole;
import com.study.NIHo.domain.user.enums.UserStatus;
import lombok.Builder;

@Builder
public record UserGetResponseDTO(long id, String email, String username, String password, UserRole userRole, UserStatus userStatus) {

    public static UserGetResponseDTO of(User user) {
        return UserGetResponseDTO.builder()
                .id(user.getId())
                .email(user.getLoginInfo().getEmail())
                .username(user.getUsername())
                .password(user.getLoginInfo().getPassword())
                .userRole(user.getStatusInfo().getUserRole())
                .userStatus(user.getStatusInfo().getUserStatus())
                .build();
    }
}
