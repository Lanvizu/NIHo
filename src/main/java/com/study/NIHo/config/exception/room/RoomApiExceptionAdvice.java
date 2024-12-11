package com.study.NIHo.config.exception.room;

import com.study.NIHo.config.exception.common.ApiExceptionEntity;
import com.study.NIHo.util.exception.room.RoomException;
import com.study.NIHo.util.exception.user.UserException;
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
public class RoomApiExceptionAdvice {
    @ExceptionHandler({UserException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest req, final RoomException e) {
        log.warn("[UserApiExceptionAdvice] UserException :: {}", e.getRoomExceptionResult().getMessage());

        return ResponseEntity
                .status(e.getRoomExceptionResult().getStatus())
                .body(ApiExceptionEntity.builder()
                        .errorCode(e.getRoomExceptionResult().getCode())
                        .errorMsg(e.getRoomExceptionResult().getMessage())
                        .build());
    }
}
