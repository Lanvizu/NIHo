package com.study.NIHo.api.room.application.impl;

import com.study.NIHo.api.room.application.RoomGetService;
import com.study.NIHo.api.room.domain.entity.Room;
import com.study.NIHo.api.room.domain.repository.RoomRepository;
import com.study.NIHo.api.room.dto.response.RoomDetailResponseDTO;
import com.study.NIHo.api.room.dto.response.RoomGetResponseDTO;
import com.study.NIHo.api.room.dto.response.RoomListResponseDTO;
import com.study.NIHo.util.exception.room.RoomException;
import com.study.NIHo.util.exception.room.RoomExceptionResult;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoomGetServiceImpl implements RoomGetService {

    private final RoomRepository roomRepository;

    /**
     * 저장된 모든 객실 정보를 로딩
     *
     * @return 객실 리스트 RoomListResponseDTO
     */
    @Override
    public RoomListResponseDTO getAllRooms() {
        List<Room> rooms = roomRepository.findAll();

        List<RoomGetResponseDTO> roomDTOs = rooms.stream()
                .map(RoomGetResponseDTO::of)
                .collect(Collectors.toList());

        return RoomListResponseDTO.of(roomDTOs);
    }

    /**
     * RoomId를 통해 해당 객실 호출
     *
     * @param roomId 객실 Id
     * @return 객실 디테일 정보 RoomDetailResponseDTO
     */
    @Override
    public RoomDetailResponseDTO getRoomDetail(final long roomId) {
        return roomRepository.findById(roomId)
                .map(RoomDetailResponseDTO::of)
                .orElseThrow(() -> new RoomException(RoomExceptionResult.NOT_EXISTS));
    }

    /**
     * 룸 조회 메서드
     *
     * @param roomId 룸 ID
     * @return Room 객체
     */
    @Override
    public Room findRoomById(final long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomException(RoomExceptionResult.NOT_EXISTS));
    }
}
