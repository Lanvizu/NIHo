package com.study.NIHo.api.room.dto.response;

import com.study.NIHo.api.room.domain.entity.Room;
import lombok.Builder;

@Builder
public record RoomGetResponseDTO(long id, String roomName, int capacity, int roomGrade) {
    public static RoomGetResponseDTO of(Room room) {
        return RoomGetResponseDTO.builder()
                .id(room.getId())
                .roomName(room.getRoomType().getRoomName())
                .capacity(room.getRoomType().getCapacity())
                .roomGrade(room.getRoomType().getRoomGrade())
                .build();
    }
}