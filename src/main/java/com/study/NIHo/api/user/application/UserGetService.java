package com.study.NIHo.api.user.application;

import com.study.NIHo.api.user.application.dto.user.response.UserGetResponseDTO;

public interface UserGetService {

    /**
     * 사용자 idx를 이용해 사용자 정보 조회
     *
     * @param id 사용자 idx
     * @return 사용자 정보 UserGetResponseDTO
     */
    UserGetResponseDTO getUserById(final long id);
}