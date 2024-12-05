package com.study.NIHo.api.user.application.impl;

import com.study.NIHo.api.user.application.UserAddService;
import com.study.NIHo.api.user.dto.request.UserAddRequestDTO;
import com.study.NIHo.api.user.domain.entity.User;
import com.study.NIHo.api.user.domain.repository.UserRepository;
import com.study.NIHo.util.exception.UserException;
import com.study.NIHo.util.exception.UserExceptionResult;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserAddServiceImpl implements UserAddService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 사용자 추가
     *
     * @param userAddRequestDTO UserAddRequestDTO
     */
    @Override
    @Transactional
    public void addUser(final UserAddRequestDTO userAddRequestDTO) {

        isExistEmail(userAddRequestDTO.getEmail());

        checkSamePassword(userAddRequestDTO.getPassword(), userAddRequestDTO.getConfirmPassword());

        // User DTO to Entity
        User user = User.of(userAddRequestDTO);

        // password 암호화
        user.getLoginInfo().encryptPassword(bCryptPasswordEncoder);

        // save
        userRepository.save(user);
    }

    private void isExistEmail(String email) {
        userRepository.findByLoginInfoEmail(email)
                .ifPresent(user -> {
                    throw new UserException(UserExceptionResult.DUPLICATE_EMAIL);
                });
    }


    private void checkSamePassword(String password, String confirmPassword) {
        Optional.of(password)
                .filter(pwd -> !pwd.equals(confirmPassword))
                .ifPresent(pwd -> {
                    throw new UserException(UserExceptionResult.PASSWORD_MISMATCH);
                });
    }
}
