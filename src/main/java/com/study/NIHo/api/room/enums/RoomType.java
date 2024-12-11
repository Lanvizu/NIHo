package com.study.NIHo.api.room.enums;

import lombok.Getter;

@Getter
public enum RoomType {
    TYPE_STANDARD_SINGLE("STANDARD SINGLE", 1, 1),
    TYPE_STANDARD_DOUBLE("STANDARD DOUBLE", 2, 1),
    TYPE_DELUXE_DOUBLE("DELUXE DOUBLE", 2, 2),
    TYPE_DELUXE_TWIN("DELUXE TWIN", 2, 2),
    TYPE_SUITE_EXECUTIVE("SUITE EXECUTIVE", 2, 4),
    TYPE_SUITE_FAMILY("SUITE FAMILY", 2, 5);

    private final String roomName;
    private final int capacity;
    private final int roomGrade;

    RoomType(String roomName, int capacity, int roomGrade) {
        this.roomName = roomName;
        this.capacity = capacity;
        this.roomGrade = roomGrade;
    }
}
