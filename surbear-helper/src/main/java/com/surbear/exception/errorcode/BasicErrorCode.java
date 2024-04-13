package com.surbear.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BasicErrorCode implements ErrorCode {

    //  400 BAD_REQUEST
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "유효하지 않은 파라미터 입니다."),


    //  401 UNAUTHORIZED
    AUTHORIZATION_HEADER_NULL(HttpStatus.UNAUTHORIZED, "인증 헤더가 null입니다"),
    INVALID_TOKEN_PREFIX(HttpStatus.UNAUTHORIZED, "헤더 토큰 Prefix가 유효하지 않습니다."),
    NOT_AUTHENTICATION(HttpStatus.UNAUTHORIZED, "인증이 실패했습니다."),


    // 403 FORBIDDEN
    BLOCKED_USER(HttpStatus.FORBIDDEN, "차단된 사용자입니다."),
    NOT_AUTHORIZATION(HttpStatus.FORBIDDEN, "인가가 실패했습니다."),
    ROLE_NOT_EXISTS(HttpStatus.FORBIDDEN, "권한이 존재하지 않습니다."),


    // 404 NOT_FOUND
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),


    // 409 CONFLICT
    DUPLICATED_ID(HttpStatus.CONFLICT, "중복된 계정입니다."),


    // 500  INTERNAL_SERVER_ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에서 처리할 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}