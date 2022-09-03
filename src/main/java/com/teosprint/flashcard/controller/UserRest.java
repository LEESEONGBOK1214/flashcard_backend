package com.teosprint.flashcard.controller;

import com.teosprint.flashcard.config.jwt.JwsTokenProvider;
import com.teosprint.flashcard.config.jwt.TokenDto;
import com.teosprint.flashcard.dto.UserDto;
import com.teosprint.flashcard.entity.User;
import com.teosprint.flashcard.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class UserRest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwsTokenProvider tokenProvider;





    @PostMapping("/join")
    public ResponseEntity<UserDto.UserInfo> join(@Valid @RequestBody UserDto.UserPost user){
        log.info("회원가입 DTO : {}", user);
        UserDto.UserInfo userInfo = userService.postUser(user);
        return new ResponseEntity<>(userInfo, HttpStatus.OK);

    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody UserDto.UserLogin user) {
        log.info("login - user = {}",user);
        TokenDto token = userService.login(user);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
