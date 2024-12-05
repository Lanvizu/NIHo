package com.study.NIHo.api.user.application.impl;

import com.study.NIHo.api.user.application.UserDeleteService;
import com.study.NIHo.api.user.domain.entity.User;
import com.study.NIHo.api.user.domain.repository.UserRepository;
import com.study.NIHo.util.exception.UserException;
import com.study.NIHo.util.exception.UserExceptionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserDeleteServiceImpl implements UserDeleteService {

    private final UserRepository userRepository;

    /**
     * 사용자 삭제
     *
     * @param id 사용자 idx
     */
    @Override
    @Transactional
    public void deleteUser(final long id) {
        // 사용자 정보 조회
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException(UserExceptionResult.NOT_EXISTS));

        // delete
        userRepository.delete(user);
    }
}
