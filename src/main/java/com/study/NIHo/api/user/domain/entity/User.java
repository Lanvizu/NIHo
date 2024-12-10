package com.study.NIHo.api.user.domain.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import com.study.NIHo.api.common.entity.BaseTimeEntity;
import com.study.NIHo.api.user.dto.request.UserAddRequestDTO;
import com.study.NIHo.api.user.enums.UserStatus;
import com.study.NIHo.api.user.domain.entity.value.LoginInfo;
import com.study.NIHo.api.user.domain.entity.value.StatusInfo;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Entity
@Builder
@Table(name = "user")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE user SET del_yn = true WHERE user_id = ?")
@SQLRestriction("del_yn = false")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Embedded
    private LoginInfo loginInfo;

    @Embedded
    private StatusInfo statusInfo;

    private String username;

    private boolean delYn = Boolean.FALSE;

    public static User of(UserAddRequestDTO dto) {

        // Login Info
        LoginInfo inputLoginInfo = LoginInfo.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();

        // status Info
        StatusInfo inputStatusInfo = StatusInfo.builder()
                .userStatus(UserStatus.NORMAL)
                .userRole(dto.getUserRole())
                .build();

        return User.builder()
                .username(dto.getUsername())
                .loginInfo(inputLoginInfo)
                .statusInfo(inputStatusInfo)
                .build();
    }
}
