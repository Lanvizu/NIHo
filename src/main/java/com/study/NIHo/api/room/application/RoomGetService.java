package com.study.NIHo.api.room.application;

import com.study.NIHo.api.room.dto.response.RoomDetailResponseDTO;
import com.study.NIHo.api.room.dto.response.RoomListResponseDTO;

public interface RoomGetService {
    /**
     * 저장된 모든 객실 정보를 로딩
     *
     * @return 객실 리스트 RoomListResponseDTO
     */
    RoomListResponseDTO getAllRooms();

    /**
     * RoomId를 통해 해당 객실 호출
     *
     * @param roomId 객실 Id
     * @return 객실 디테일 정보 RoomDetailResponseDTO
     */
    RoomDetailResponseDTO getRoomDetail(Long roomId);
}
