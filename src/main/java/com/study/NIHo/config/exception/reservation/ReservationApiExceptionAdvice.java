package com.study.NIHo.config.exception.reservation;

import com.study.NIHo.config.exception.common.ApiExceptionEntity;
import com.study.NIHo.util.exception.reservation.ReservationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class ReservationApiExceptionAdvice {
    @ExceptionHandler({ReservationException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest req, final ReservationException e) {
        log.warn("[ReservationApiExceptionAdvice] ReservationException :: {}", e.getReservationExceptionResult().getMessage());

        return ResponseEntity
                .status(e.getReservationExceptionResult().getStatus())
                .body(ApiExceptionEntity.builder()
                        .errorCode(e.getReservationExceptionResult().getCode())
                        .errorMsg(e.getReservationExceptionResult().getMessage())
                        .build());
    }
}

