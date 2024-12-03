package com.study.NIHo.api.user.application.impl;

import com.study.NIHo.api.user.application.dto.user.response.UserGetResponseDTO;
import com.study.NIHo.util.exception.UserExceptionResult;
import com.study.NIHo.repository.UserRepository;
import com.study.NIHo.api.user.application.UserGetService;
import com.study.NIHo.util.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserGetServiceImpl implements UserGetService {

    private final UserRepository userRepository;

    /**
     * 사용자 id를 이용해 사용자 정보 조회
     *
     * @param id 사용자 id
     * @return 사용자 정보 UserGetResponseDTO
     */
    @Override
    public UserGetResponseDTO getUserById(final long id) {
        return userRepository.findById(id)
                .map(UserGetResponseDTO::of)
                .orElseThrow(() -> new UserException(UserExceptionResult.NOT_EXISTS));
    }
}
