package com.jwt.backend.global.exception;

import org.springframework.http.HttpStatus;

public interface CoustomExceptionType {
    int getErrorCode();

    HttpStatus getHttpStatus();

    String getErrorMessage();
}