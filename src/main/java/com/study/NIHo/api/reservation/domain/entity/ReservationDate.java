package com.study.NIHo.api.reservation.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class ReservationDate {

    @Column(name = "check_in_date", nullable = false)
    private String checkInDate;

    @Column(name = "check_out_date", nullable = false)
    private String checkOutDate;

    public ReservationDate(LocalDateTime checkIn, LocalDateTime checkOut) {
        this.checkInDate = formatDateTime(checkIn);
        this.checkOutDate = formatDateTime(checkOut);
    }

    @PrePersist
    @PreUpdate
    public void validateDates() {
        if (LocalDateTime.parse(checkInDate, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))
                .isAfter(LocalDateTime.parse(checkOutDate, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))) {
            throw new IllegalArgumentException("체크인 날짜는 체크아웃 날짜보다 이후일 수 없습니다.");
        }
    }

    private String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }
}