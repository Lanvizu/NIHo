package com.study.NIHo.api.reservation.domain.repository;

import com.study.NIHo.api.reservation.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
