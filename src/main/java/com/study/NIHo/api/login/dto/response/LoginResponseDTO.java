package com.study.NIHo.api.login.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import org.springframework.http.ResponseCookie;

@Builder
public record LoginResponseDTO(@JsonIgnore ResponseCookie accessTokenCookie, String refreshToken) {
}
