package com.study.NIHo.api.user.application;

import com.study.NIHo.domain.user.User;
import com.study.NIHo.repository.UserRepository;
import com.study.NIHo.api.user.application.dto.user.request.SignUpDto;
import com.study.NIHo.api.user.application.dto.user.response.ResponseEmailDto;
import com.study.NIHo.util.exception.UserException;
import com.study.NIHo.util.exception.UserExceptionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//@Transactional
public class UserService {
    private final UserRepository userRepository;

//    controller -> service : service Dto
    public ResponseEmailDto signUp(SignUpDto dto) {

        isExistEmail(dto.getEmail());
        checkSamePassword(dto.getPassword(), dto.getConfirmPassword());

        User user = userRepository.save(dto.ofEntity());

        return ResponseEmailDto.toEmailDto(user);
    }

    private void isExistEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
//            Exception Enum file
            throw new UserException(UserExceptionResult.DUPLICATE_EMAIL);
        }
    }

    private void checkSamePassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new UserException(UserExceptionResult.PASSWORD_MISMATCH);
        }
    }


}
