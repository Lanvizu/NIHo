package com.study.NIHo.util.exception.reservation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReservationException extends RuntimeException {
  private final ReservationExceptionResult reservationExceptionResult;
}
