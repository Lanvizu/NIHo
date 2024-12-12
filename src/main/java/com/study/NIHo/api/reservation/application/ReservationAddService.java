package com.study.NIHo.api.reservation.application;

import com.study.NIHo.api.reservation.dto.request.ReservationAddRequestDTO;

public interface ReservationAddService {
    /**
     * 객실 예약 추가
     *
     * @param userId roomId reservationAddRequestDTO
     */
    void addReservation(final long userId,
                        final long roomId,
                        final ReservationAddRequestDTO reservationAddRequestDTO);
}
