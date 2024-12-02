package com.study.NIHo.controller.api;

import com.study.NIHo.domain.user.User;
import com.study.NIHo.service.UserService;
import com.study.NIHo.service.dto.user.req.SignUpDto;
import com.study.NIHo.service.dto.user.res.ResEmailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
public class LoginApiController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/register")
    public ResponseEntity<ResEmailDto> register(@RequestBody SignUpDto dto) {
        ResEmailDto resEmailDto = userService.signUp(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resEmailDto);
    }
}
