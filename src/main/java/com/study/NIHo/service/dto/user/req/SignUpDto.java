package com.study.NIHo.service.dto.user.req;

import com.study.NIHo.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpDto {

    private final String username;
    private final String email;
    private final String password;
    private final String confirmPassword;

    @Builder
    public SignUpDto(String username, String email, String password, String confirmPassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public User ofEntity() {
        return User.builder()
                .username(this.getUsername())
                .email(this.getEmail())
                .password(this.getPassword())
                .build();
    }
}
