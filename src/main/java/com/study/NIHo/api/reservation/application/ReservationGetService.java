package com.study.NIHo.api.reservation.application;

import com.study.NIHo.api.reservation.dto.response.ReservationGetListResponseDTO;

public interface ReservationGetService {
    /**
     * 오늘 이후 예약 조회
     * @param roomId 선택한 객실
     * @return 예약 리스트 ReservationGetListResponseDTO
     */
    ReservationGetListResponseDTO getReservationsFromDate(Long roomId);
}
