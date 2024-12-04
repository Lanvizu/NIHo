package com.study.NIHo.api.login.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Login Exception
 */
@Getter
@RequiredArgsConstructor
public class LoginException extends RuntimeException {

    private final LoginExceptionResult loginErrorResult;

}
