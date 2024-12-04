package com.study.NIHo.api.user.application;

import com.study.NIHo.api.user.dto.response.UserGetResponseDTO;

public interface UserGetService {

    /**
     * 사용자 idx를 이용해 사용자 정보 조회
     *
     * @param id 사용자 idx
     * @return 사용자 정보 UserGetResponseDTO
     */
    UserGetResponseDTO getUserById(final long id);

    /**
     * 사용자 email를 이용해 사용자 정보 조회
     *
     * @param email 사용자 email
     * @return 사용자 정보 UserGetResponseDTO
     */
    UserGetResponseDTO getUserByEmail(final String email);
}
