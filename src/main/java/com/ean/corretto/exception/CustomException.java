package com.ean.corretto.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final int code;
    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }
}
