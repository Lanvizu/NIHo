package com.study.NIHo.api.room.dto.response;

import java.util.List;

public record RoomListResponseDTO(List<RoomGetResponseDTO> rooms) {
    public static RoomListResponseDTO of(List<RoomGetResponseDTO> roomDTOs) {
        return new RoomListResponseDTO(roomDTOs);
    }
}