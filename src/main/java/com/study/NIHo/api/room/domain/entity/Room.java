package com.study.NIHo.api.room.domain.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import com.study.NIHo.api.room.enums.RoomType;
import com.study.NIHo.api.user.domain.entity.User;
import com.study.NIHo.api.user.domain.entity.value.LoginInfo;
import com.study.NIHo.api.user.domain.entity.value.StatusInfo;
import com.study.NIHo.api.user.dto.request.UserAddRequestDTO;
import com.study.NIHo.api.user.enums.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@Table(name = "room")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type")
    private RoomType roomType;

    private int price;
}