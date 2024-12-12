package com.study.NIHo.api.reservation.controller;

import com.study.NIHo.api.common.response.entity.ApiResponseEntity;
import com.study.NIHo.api.reservation.application.ReservationAddService;
import com.study.NIHo.api.reservation.dto.request.ReservationAddRequestDTO;
import com.study.NIHo.api.user.application.UserGetService;
import com.study.NIHo.util.jwt.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationAddService reservationAddService;
    private final UserGetService userGetService;

    @PostMapping("/room/{roomId}/reservation")
    public ResponseEntity<ApiResponseEntity> addReservation(@PathVariable("roomId") Long roomId,
                                                            @RequestBody @Valid ReservationAddRequestDTO reservationAddRequestDTO,
                                                            Authentication authentication) {
        long userId = (JwtUtil.getLoginId(authentication));
        reservationAddService.addReservation(userId, roomId, reservationAddRequestDTO);
        return ApiResponseEntity.successResponseEntity();
    }
}
