package com.study.NIHo.api.reservation.domain.repository;

import com.study.NIHo.api.reservation.domain.entity.Reservation;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
            "FROM Reservation r " +
            "WHERE r.room.id = :roomId " +
            "AND r.reservationDate.checkInDate < :checkOut " +
            "AND r.reservationDate.checkOutDate > :checkIn")
    boolean existsByRoomAndReservationDateOverlap(@Param("roomId") Long roomId,
                                                  @Param("checkIn") LocalDate checkIn,
                                                  @Param("checkOut") LocalDate checkOut);

    @Query("SELECT r" +
            " FROM Reservation r" +
            " WHERE r.reservationDate.checkOutDate > :startDate" +
            " AND r.reservationDate.checkInDate < :endDate" +
            " AND r.room.id = :roomId")
    List<Reservation> findReservationsInDateRange(@Param("startDate") LocalDate startDate,
                                                  @Param("endDate") LocalDate endDate,
                                                  @Param("roomId") Long roomId);
}
