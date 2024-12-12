package com.study.NIHo.api.reservation.application.impl;

import com.study.NIHo.api.reservation.application.ReservationGetService;
import com.study.NIHo.api.reservation.domain.entity.Reservation;
import com.study.NIHo.api.reservation.domain.repository.ReservationRepository;
import com.study.NIHo.api.reservation.dto.response.ReservationGetListResponseDTO;
import com.study.NIHo.api.reservation.dto.response.ReservationGetResponseDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationGetServiceImpl implements ReservationGetService {

    private final ReservationRepository reservationRepository;

    /**
     * 오늘 이후 예약 조회
     * @param roomId 선택한 객실
     * @return 예약 리스트 ReservationGetListResponseDTO
     */
    @Override
    public ReservationGetListResponseDTO getReservationsFromDate(Long roomId) {
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusMonths(3);
        List<Reservation> reservations = reservationRepository.findReservationsInDateRange(today, endDate, roomId);

        List<ReservationGetResponseDTO> responseDTOS = reservations.stream()
                .map(ReservationGetResponseDTO::of)
                .collect(Collectors.toList());
        return ReservationGetListResponseDTO.of(responseDTOS);
    }
}
