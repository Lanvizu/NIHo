package com.study.NIHo.api.room.controller;

import com.study.NIHo.api.common.response.entity.ApiResponseEntity;
import com.study.NIHo.api.reservation.application.ReservationGetService;
import com.study.NIHo.api.room.application.RoomGetService;
import com.study.NIHo.api.room.dto.response.RoomDetailResponseDTO;
import com.study.NIHo.api.room.dto.response.RoomListResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {
    private final RoomGetService roomGetService;
    private final ReservationGetService reservationGetService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponseEntity> getRoomList() {
        RoomListResponseDTO allRooms = roomGetService.getAllRooms();
        return ApiResponseEntity.successResponseEntity(allRooms);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<ApiResponseEntity> getRoom(@PathVariable long roomId) {
        RoomDetailResponseDTO roomDetail = roomGetService.getRoomDetail(roomId);
        return ApiResponseEntity.successResponseEntity(roomDetail);
    }
}
