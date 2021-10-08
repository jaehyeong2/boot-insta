package com.example.instaclone.controller;

import com.example.instaclone.domian.user.User;
import com.example.instaclone.dto.SignUpDto;
import com.example.instaclone.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

//    @Autowired
//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }

    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

    @PostMapping("/auth/signup")
    public String signup(SignUpDto signUpDto) {
        User user = signUpDto.toEntity();
        User userEntity = authService.signin(user);
        return "auth/signin";
    }
}
