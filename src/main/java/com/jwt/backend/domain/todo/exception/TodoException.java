package com.jwt.backend.domain.todo.exception;

import com.jwt.backend.global.exception.CoustomException;
import com.jwt.backend.global.exception.CoustomExceptionType;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class TodoException extends CoustomException {

    private CoustomExceptionType exceptionType;

    @Override
    public CoustomExceptionType getExceptionType() {
        return exceptionType;
    }
}
