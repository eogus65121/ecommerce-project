package com.done.ecommerce.common.exception.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponse {

    private int errorCode;
    private String errorMessage;
    private String errorDetailMessage;

    // error 상세 메세지가 없는 경우
    public ErrorResponse(ExceptionType exceptionType){
        this.errorCode = exceptionType.getErrorCode();
        this.errorMessage = exceptionType.getErrorMessage();
        this.errorDetailMessage = exceptionType.getErrorDetailMessage();
    }

    // error 상세 메세지가 있는 경우
    public ErrorResponse(ExceptionType exceptionType, String errorDetailMessage){
        this.errorCode = exceptionType.getErrorCode();
        this.errorMessage = exceptionType.getErrorMessage();
        this.errorDetailMessage = errorDetailMessage;
    }
}
