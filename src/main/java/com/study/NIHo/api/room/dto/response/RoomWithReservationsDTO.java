package com.study.NIHo.api.room.dto.response;

import com.study.NIHo.api.reservation.dto.response.ReservationGetListResponseDTO;
import com.study.NIHo.api.reservation.dto.response.ReservationGetResponseDTO;
import java.util.List;

public record RoomWithReservationsDTO(RoomDetailResponseDTO roomDetail, List<ReservationGetResponseDTO> reservations) {
    public static RoomWithReservationsDTO of(RoomDetailResponseDTO roomDetail, ReservationGetListResponseDTO reservationList) {
        return new RoomWithReservationsDTO(roomDetail, reservationList.reservations());
    }
}

