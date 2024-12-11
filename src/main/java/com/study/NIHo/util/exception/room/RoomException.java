package com.study.NIHo.util.exception.room;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RoomException extends RuntimeException {
    private final RoomExceptionResult roomExceptionResult;
}
