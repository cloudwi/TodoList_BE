package com.jwt.backend.domain.Todo.exception;

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
public enum TodoExceptionType implements BaseExceptionType {

    NOT_MATCHING_TODO(605, HttpStatus.NOT_FOUND,"해당 게시물은 작성하지 않았습니다."),
    NOT_FOUND_TODO(606, HttpStatus.NOT_FOUND, "존재하지 않는 TODO입니다.");

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
