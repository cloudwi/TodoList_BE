package com.jwt.backend.global.exception;

public abstract class CoustomException extends RuntimeException {
    public abstract CoustomExceptionType getExceptionType();
}
