package com.study.NIHo.service.dto.user.res;

import com.study.NIHo.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResEmailDto {

    private final String email;

    @Builder
    public ResEmailDto(String email) {
        this.email = email;
    }

    public static ResEmailDto toEmailDto(User user) {
        return ResEmailDto.builder()
                .email(user.getEmail()).build();
    }
}
