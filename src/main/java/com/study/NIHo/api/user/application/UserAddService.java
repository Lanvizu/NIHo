package com.study.NIHo.api.user.application;

import com.study.NIHo.api.user.dto.request.UserAddRequestDTO;

public interface UserAddService {
    /**
     * 사용자 추가
     *
     * @param userAddRequestDTO UserAddRequestDTO
     */
    void addUser(final UserAddRequestDTO userAddRequestDTO);
}
