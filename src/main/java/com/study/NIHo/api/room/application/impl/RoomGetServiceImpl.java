package com.study.NIHo.api.room.application.impl;

import com.study.NIHo.api.room.application.RoomGetService;
import com.study.NIHo.api.room.domain.entity.Room;
import com.study.NIHo.api.room.domain.repository.RoomRepository;
import com.study.NIHo.api.room.dto.response.RoomDetailResponseDTO;
import com.study.NIHo.api.room.dto.response.RoomGetResponseDTO;
import com.study.NIHo.api.room.dto.response.RoomListResponseDTO;
import com.study.NIHo.util.exception.room.RoomException;
import com.study.NIHo.util.exception.room.RoomExceptionResult;
import com.study.NIHo.util.exception.user.UserExceptionResult;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoomGetServiceImpl implements RoomGetService {

    private final RoomRepository roomRepository;

    @Override
    public RoomListResponseDTO getAllRooms() {
        List<Room> rooms = roomRepository.findAll();

        List<RoomGetResponseDTO> roomDTOs = rooms.stream()
                .map(RoomGetResponseDTO::of)
                .collect(Collectors.toList());

        return RoomListResponseDTO.of(roomDTOs);
    }

    @Override
    public RoomDetailResponseDTO getRoomDetail(Long roomId) {
        return roomRepository.findById(roomId)
                .map(RoomDetailResponseDTO::of)
                .orElseThrow(() -> new RoomException(RoomExceptionResult.NOT_EXISTS));
    }
}
