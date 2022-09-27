package com.jwt.backend.domain.member.exception;

import com.jwt.backend.global.exception.BaseException;
import com.jwt.backend.global.exception.BaseExceptionType;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class MemberException extends BaseException {

    private BaseExceptionType exceptionType;

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
