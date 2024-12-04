package com.study.NIHo.api.login.application.impl;

import com.study.NIHo.api.login.application.LoginService;
import com.study.NIHo.api.login.dto.request.LoginRequestDTO;
import com.study.NIHo.api.login.dto.response.LoginResponseDTO;
import com.study.NIHo.api.login.exception.LoginException;
import com.study.NIHo.api.login.exception.LoginExceptionResult;
import com.study.NIHo.api.token.vo.RefreshToken;
import com.study.NIHo.api.user.application.UserGetService;
import com.study.NIHo.api.user.dto.response.UserGetResponseDTO;
import com.study.NIHo.config.login.security.provide.JwtProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserGetService userGetService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtProvider jwtProvider;

    @Override
    @Transactional
    public LoginResponseDTO login(final LoginRequestDTO loginRequestDTO) {
        // 사용자 정보 조회
        UserGetResponseDTO userInfo = userGetService.getUserByEmail(loginRequestDTO.getEmail());

        // password 일치 여부 체크
        if(!bCryptPasswordEncoder.matches(loginRequestDTO.getPassword(), userInfo.password()))
            throw new LoginException(LoginExceptionResult.NOT_CORRECT);

        // jwt 토큰 생성
        String accessToken = jwtProvider.generateAccessToken(userInfo.id());

        // 기존에 가지고 있는 사용자의 refresh token 제거
        RefreshToken.removeUserRefreshToken(userInfo.id());

        // refresh token 생성 후 저장
        String refreshToken = jwtProvider.generateRefreshToken(userInfo.id());
        RefreshToken.putRefreshToken(refreshToken, userInfo.id());

        return LoginResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}
