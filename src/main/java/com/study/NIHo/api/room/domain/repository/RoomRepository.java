package com.study.NIHo.api.room.domain.repository;

import com.study.NIHo.api.room.domain.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
