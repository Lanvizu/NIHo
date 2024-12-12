package com.study.NIHo.api.reservation.domain.entity;

import com.study.NIHo.api.common.entity.BaseTimeEntity;
import com.study.NIHo.api.reservation.domain.entity.value.ReservationDate;
import com.study.NIHo.api.reservation.dto.request.ReservationAddRequestDTO;
import com.study.NIHo.api.reservation.enums.ReservationStatus;
import com.study.NIHo.api.room.domain.entity.Room;
import com.study.NIHo.api.user.domain.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@Table(name = "reservation")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Embedded
    private ReservationDate reservationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;

    public static Reservation of(ReservationAddRequestDTO dto, User user, Room room) {
        return Reservation.builder()
                .user(user)
                .room(room)
                .reservationDate(new ReservationDate(dto.getCheckInDate(), dto.getCheckOutDate()))
                .status(ReservationStatus.RESERVED)
                .build();
    }
}