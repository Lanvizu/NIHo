package com.study.NIHo.api.reservation.application.impl;

import com.study.NIHo.api.reservation.application.ReservationAddService;
import com.study.NIHo.api.reservation.domain.entity.Reservation;
import com.study.NIHo.api.reservation.domain.repository.ReservationRepository;
import com.study.NIHo.api.reservation.dto.request.ReservationAddRequestDTO;
import com.study.NIHo.api.room.application.RoomGetService;
import com.study.NIHo.api.room.domain.entity.Room;
import com.study.NIHo.api.user.application.UserGetService;
import com.study.NIHo.api.user.domain.entity.User;
import com.study.NIHo.util.exception.reservation.ReservationException;
import com.study.NIHo.util.exception.reservation.ReservationExceptionResult;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReservationAddServiceImpl implements ReservationAddService {

    private final ReservationRepository reservationRepository;
    private final UserGetService userGetService;
    private final RoomGetService roomGetService;

    /**
     * 객실 예약 추가
     *
     * @param userId roomId reservationAddRequestDTO
     */
    @Override
    @Transactional
    public void addReservation(final long userId,
                               final long roomId,
                               final ReservationAddRequestDTO reservationAddRequestDTO) {
        // 예약 생성
        Reservation reservation = createReservation(userId, roomId, reservationAddRequestDTO);

        // 예약 저장
        reservationRepository.save(reservation);
    }

    /**
     * 객실 예약 생성
     *
     * @param dto ReservationAddRequestDTO
     * @return 생성된 객실 예약 객체
     */
    private Reservation createReservation(long userId, long roomId, ReservationAddRequestDTO dto) {
        // 유저 조회
        User user = userGetService.findUserById(userId);

        // 룸 조회
        Room room = roomGetService.findRoomById(roomId);

        // 예약 날짜 중복 체크
        validateReservationDates(room, dto.getCheckInDate(), dto.getCheckOutDate());

        // Reservation 객체 생성 및 반환
        return Reservation.of(dto, user, room);
    }

    /**
     * 예약 날짜 중복 체크 메서드
     *
     * @param room     Room 객체
     * @param checkIn  체크인 날짜
     * @param checkOut 체크아웃 날짜
     */
    private void validateReservationDates(Room room, LocalDate checkIn, LocalDate checkOut) {
        boolean isOverlapping = reservationRepository.existsByRoomAndReservationDateOverlap(room.getId(), checkIn,
                checkOut);

        if (isOverlapping) {
            throw new ReservationException(ReservationExceptionResult.ALREADY_RESERVED);
        }

        if (checkIn.isAfter(checkOut)) {
            throw new ReservationException(ReservationExceptionResult.INVALID_DATE_RANGE);
        }
    }
}
