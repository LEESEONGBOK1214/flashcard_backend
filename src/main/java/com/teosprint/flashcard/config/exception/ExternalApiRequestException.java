package com.teosprint.flashcard.config.exception;

public class ExternalApiRequestException extends BasicException {
    public ExternalApiRequestException(String address, String message){
        super(500, "요청url : "+address, message);
    }
}