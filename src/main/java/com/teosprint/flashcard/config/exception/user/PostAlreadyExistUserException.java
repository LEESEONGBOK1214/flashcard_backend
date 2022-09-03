package com.teosprint.flashcard.config.exception.user;

import com.teosprint.flashcard.config.exception.BasicException;

public class PostAlreadyExistUserException extends BasicException {
    public PostAlreadyExistUserException() {
        super(409, "conflict", "이미 회원가입된 username입니다.");
    }

}