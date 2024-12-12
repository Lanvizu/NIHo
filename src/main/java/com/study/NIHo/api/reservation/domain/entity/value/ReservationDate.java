package com.study.NIHo.api.reservation.domain.entity.value;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class ReservationDate {

    @Column(name = "check_in_date", nullable = false)
    private LocalDate checkInDate;

    @Column(name = "check_out_date", nullable = false)
    private LocalDate checkOutDate;

    public ReservationDate(LocalDate checkIn, LocalDate checkOut) {
        this.checkInDate = checkIn;
        this.checkOutDate = checkOut;
    }
}