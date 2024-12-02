package com.study.NIHo.domain.user;

import static jakarta.persistence.GenerationType.IDENTITY;

import com.study.NIHo.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserLevel userLevel;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Builder
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userLevel = UserLevel.USER;
        this.userStatus = UserStatus.NORMAL;
    }
}
