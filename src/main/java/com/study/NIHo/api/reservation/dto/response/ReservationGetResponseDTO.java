package com.study.NIHo.api.reservation.dto.response;

import com.study.NIHo.api.reservation.domain.entity.Reservation;
import java.time.LocalDate;
import lombok.Builder;

@Builder
public record ReservationGetResponseDTO(LocalDate checkInDate, LocalDate checkOutDate) {
    public static ReservationGetResponseDTO of(Reservation reservation) {
        return ReservationGetResponseDTO.builder()
                .checkInDate(reservation.getReservationDate().getCheckInDate())
                .checkOutDate(reservation.getReservationDate().getCheckOutDate())
                .build();
    }
}
