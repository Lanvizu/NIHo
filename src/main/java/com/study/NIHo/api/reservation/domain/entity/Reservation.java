package com.study.NIHo.api.reservation.domain.entity;

import com.study.NIHo.api.common.entity.BaseTimeEntity;
import com.study.NIHo.api.room.domain.entity.Room;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Embedded
    private ReservationDate reservationDate;

    public Reservation(Room room, LocalDateTime checkIn, LocalDateTime checkOut) {
        this.room = room;
        this.reservationDate = new ReservationDate(checkIn, checkOut);
    }

    public void updateReservationDate(LocalDateTime checkIn, LocalDateTime checkOut) {
        this.reservationDate = new ReservationDate(checkIn, checkOut);
    }
}