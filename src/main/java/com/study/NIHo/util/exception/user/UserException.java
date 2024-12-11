package com.study.NIHo.util.exception.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserException extends RuntimeException {

    private final UserExceptionResult userExceptionResult;

}