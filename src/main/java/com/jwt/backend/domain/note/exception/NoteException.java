package com.jwt.backend.domain.note.exception;

import com.jwt.backend.global.exception.CoustomException;
import com.jwt.backend.global.exception.CoustomExceptionType;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class NoteException extends CoustomException {

    private CoustomExceptionType exceptionType;

    @Override
    public CoustomExceptionType getExceptionType() {
        return exceptionType;
    }
}
