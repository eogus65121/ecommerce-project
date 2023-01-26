package com.done.ecommerce.common.exception.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionType {

    BAD_REQUEST(400, HttpStatus.BAD_REQUEST, "잘못된 요청입니다.", "")
    ,UNAUTHORIZED(401, HttpStatus.UNAUTHORIZED, "인증되지 않은 요청입니다.", "")
    ,FORBIDDEN(403, HttpStatus.FORBIDDEN, "해당 리소스에 접근 권한이 없습니다.", "")
    ,NOT_FOUND(404, HttpStatus.NOT_FOUND, "해당 리소스를 찾을 수 없습니다.", "")
    ,UNPROCESSABLE(422, HttpStatus.UNPROCESSABLE_ENTITY, "요청이 거부되었습니다.", "")
    ,INTERNAL_SERVER_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR, "요청이 거부되었습니다.", "")
    ,BAD_GATEWAY(502, HttpStatus.BAD_GATEWAY, "요청이 거부되었습니다.", "");

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;
    private String errorDetailMessage;

}
