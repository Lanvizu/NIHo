package com.study.NIHo.api.user.application.impl;

import com.study.NIHo.api.user.dto.response.UserGetResponseDTO;
import com.study.NIHo.util.exception.UserExceptionResult;
import com.study.NIHo.api.user.domain.repository.UserRepository;
import com.study.NIHo.api.user.application.UserGetService;
import com.study.NIHo.util.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserGetServiceImpl implements UserGetService {

    private final UserRepository userRepository;

    /**
     * 사용자 idx를 이용해 사용자 정보 조회
     *
     * @param id 사용자 idx
     * @return 사용자 정보 UserGetResponseDTO
     */
    @Override
    public UserGetResponseDTO getUserById(final long id) {
        return userRepository.findById(id)
                .map(UserGetResponseDTO::of)
                .orElseThrow(() -> new UserException(UserExceptionResult.NOT_EXISTS));
    }

    /**
     * 사용자 email을 이용해 사용자 정보 조회
     *
     * @param email 사용자 id
     * @return 사용자 정보 UserGetResponseDTO
     */
    @Override
    public UserGetResponseDTO getUserByEmail(final String email) {
        return userRepository.findByLoginInfoEmail(email)
                .map(UserGetResponseDTO::of)
                .orElseThrow(() -> new UserException(UserExceptionResult.NOT_EXISTS));
    }
}
