package com.study.NIHo.domain.user.value;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Embeddable
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginInfo {

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    /**
     * password 암호화
     *
     * @param passwordEncoder PasswordEncoder
     */
    public void encryptPassword(PasswordEncoder passwordEncoder) {
        // 비밀번호가 빈 값인 경우 exception
        if(password == null || password.isEmpty()) {
            throw new IllegalArgumentException("empty password!");
        }

        // 암호화
        this.password = passwordEncoder.encode(this.password);
    }
}
