package com.study.NIHo.api.reservation.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record ReservationGetListResponseDTO(List<ReservationGetResponseDTO> reservations) {
    public static ReservationGetListResponseDTO of(List<ReservationGetResponseDTO> reservations) {
        return new ReservationGetListResponseDTO(reservations);
    }
}