package com.study.NIHo.service;

import com.study.NIHo.domain.user.User;
import com.study.NIHo.repository.UserRepository;
import com.study.NIHo.service.dto.user.req.SignUpDto;
import com.study.NIHo.service.dto.user.res.ResEmailDto;
import com.study.NIHo.util.exception.UserException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//@Transactional
public class UserService {
    private final UserRepository userRepository;

//    controller -> service : service Dto
    public ResEmailDto signUp(SignUpDto dto) {

        isExistEmail(dto.getEmail());
        checkSamePassword(dto.getPassword(), dto.getConfirmPassword());

        User user = userRepository.save(dto.ofEntity());

        return ResEmailDto.toEmailDto(user);
    }

    private void isExistEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
//            Exception Enum file
            throw new UserException("이미 사용 중인 이메일입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    private void checkSamePassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new UserException("패스워드 불일치", HttpStatus.BAD_REQUEST);
        }
    }
}
