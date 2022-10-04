package com.jwt.backend.global.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //공통 예외
    BAD_REQUEST_PARAM(HttpStatus.BAD_REQUEST,"잘못된 요청입니다."),
    BAD_REQUEST_VALIDATION(HttpStatus.BAD_REQUEST, "검증에 실패하였습니다."),

    //파일 예외
    NOT_FOUND_FILE(HttpStatus.NOT_FOUND, "해당 파일을 찾을 수 없습니다."),

    //회원 예외
    UNAUTHORIZED_MEMBER(HttpStatus.UNAUTHORIZED, "해당 요청은 로그인이 필요합니다."),
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다."),
    EXIST_MEMBER(HttpStatus.BAD_REQUEST, "이미 등록된 유저입니다."),
    NOT_FOUND_PASSWORD(HttpStatus.BAD_REQUEST,"일치하지 않는 비밀번호 입니다."),
    //인증 인가 예외
    FORBIDDEN_MEMBER(HttpStatus.FORBIDDEN, "해당 요청에 권한이 없습니다."),
    INVALID_AUTHORIZATION(HttpStatus.BAD_REQUEST, "인증 정보가 부정확합니다."),

    IMAGE_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드에 실패하였습니다."),

    //스택 예외
    NOT_FOUND_STACK(HttpStatus.NOT_FOUND, "해당 기술 스택을 찾을 수 없습니다."),

    //프로젝트 예외
    NOT_FOUND_PROJECT(HttpStatus.NOT_FOUND, "해당 프로젝트를 찾을 수 없습니다."),
    //댓글 예외
    NOT_FOUND_COMMENT(HttpStatus.NOT_FOUND, "해당 댓글을 찾을 수 없습니다."),
    //노트 예외
    NOT_FOUND_NOTE(HttpStatus.NOT_FOUND,"해당 노트를 찾을 수 없습니다."),
    NOT_MATCHING_NOTE(HttpStatus.NOT_EXTENDED,"해당 게시물은 소유하고 있지 않습니다."),
    //todo 예외
    NOT_MATCHING_TODO(HttpStatus.NOT_EXTENDED,"해당 TODO은 소유하고 있지 않습니다."),
    NOT_FOUND_TODO(HttpStatus.NOT_FOUND,"해당 TODO를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}