package com.teosprint.flashcard.config.exception;


public class ForbiddenException extends BasicException {

    public ForbiddenException(String message){
        super(403, "forbidden", message);
    }

    public ForbiddenException() {
        this("권한이 없습니다.");
    }
}