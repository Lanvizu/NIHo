package com.study.NIHo.api.room.dto.response;

import com.study.NIHo.api.room.domain.entity.Room;
import lombok.Builder;

@Builder
public record RoomDetailResponseDTO(Long id, String roomName, int capacity, int roomGrade, int price) {
    public static RoomDetailResponseDTO of(Room room) {
        return RoomDetailResponseDTO.builder()
                .id(room.getId())
                .roomName(room.getRoomType().getRoomName())
                .capacity(room.getRoomType().getCapacity())
                .roomGrade(room.getRoomType().getRoomGrade())
                .price(room.getPrice())
                .build();
    }
}
