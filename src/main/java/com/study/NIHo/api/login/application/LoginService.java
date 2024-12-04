package com.study.NIHo.api.login.application;

import com.study.NIHo.api.login.dto.request.LoginRequestDTO;
import com.study.NIHo.api.login.dto.response.LoginResponseDTO;

public interface LoginService {
    LoginResponseDTO login(final LoginRequestDTO loginRequestDTO);
}
