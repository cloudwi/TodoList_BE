package com.jwt.backend.domain.note.exception;

import com.jwt.backend.global.exception.BaseExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
*
*   MemberExceptionType의 설명을 여기에 작성한다.
*
*   @author jangjuyeong
*   @version 1.0.0
*   작성일 2022/09/27
**/

@Getter
@AllArgsConstructor
public enum NoteExceptionType implements BaseExceptionType {

    NOT_FOUND_NOTE(607, HttpStatus.NOT_FOUND, "존재하지 않는 노트입니다."),
    NOT_MATCHING_NOTE(608, HttpStatus.NOT_FOUND,"해당 노트는 다른 사람 노트입니다.");

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
