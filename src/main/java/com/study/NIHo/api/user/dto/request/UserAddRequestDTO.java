package com.study.NIHo.api.user.dto.request;

import com.study.NIHo.api.user.enums.UserRole;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserAddRequestDTO {

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String confirmPassword;

    private UserRole userRole = UserRole.ROLE_USER;

    @Builder
    public UserAddRequestDTO(String email, String username, String password, String confirmPassword, UserRole userRole) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.userRole = (userRole != null) ? userRole : UserRole.ROLE_USER; // 입력된 값이 있으면 사용
    }
}
