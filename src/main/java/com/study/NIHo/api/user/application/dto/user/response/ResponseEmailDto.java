package com.study.NIHo.api.user.application.dto.user.response;

import com.study.NIHo.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseEmailDto {

    private final String email;

    @Builder
    public ResponseEmailDto(String email) {
        this.email = email;
    }

    public static ResponseEmailDto toEmailDto(User user) {
        return ResponseEmailDto.builder()
                .email(user.getEmail()).build();
    }
}
