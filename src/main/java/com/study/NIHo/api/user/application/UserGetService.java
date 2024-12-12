package com.study.NIHo.api.user.application;

import com.study.NIHo.api.user.domain.entity.User;
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

    /**
     * 유저 조회 메서드
     *
     * @param userId 유저 ID
     * @return User 객체
     */
    User findUserById(final long userId);
}
