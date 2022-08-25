package com.ean.corretto.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_EXISTS_BOARD(4000,"존재하지 않는 게시판 입니다."),
    NOT_EXISTS_ARTICLE(4001, "존재하지 않는 게시글 입니다.");
    private final int code;
    private final String message;
}
