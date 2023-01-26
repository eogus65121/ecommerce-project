package com.done.ecommerce.common.exception;

import com.done.ecommerce.common.exception.vo.ErrorResponse;
import com.done.ecommerce.common.exception.vo.ExceptionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.naming.AuthenticationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * DEFAULT
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e){
        log.debug("{}", e.getMessage());
        ExceptionType exceptionType = ExceptionType.UNPROCESSABLE;

        ErrorResponse errorResponse = new ErrorResponse(exceptionType);

        return ResponseEntity
                .status(exceptionType.getHttpStatus())
                .body(errorResponse);
    }

    /**
     * UNAUTHORIZED
     */
    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException e){
        log.debug("{}", e.getMessage());
        ExceptionType exceptionType = ExceptionType.UNAUTHORIZED;

        ErrorResponse errorResponse = new ErrorResponse(exceptionType);

        return ResponseEntity
                .status(exceptionType.getHttpStatus())
                .body(errorResponse);
    }

    /**
     * NOT_FOUND
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNoHandlerException(NoHandlerFoundException e){
        log.debug("{}", e.getMessage());
        ExceptionType exceptionType = ExceptionType.NOT_FOUND;

        ErrorResponse errorResponse = new ErrorResponse(exceptionType);

        return ResponseEntity
                .status(exceptionType.getHttpStatus())
                .body(errorResponse);
    }

    /**
     * BAD_REQUEST
     */
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorResponse> handleIllegalArgumentException(NoHandlerFoundException e){
        log.debug("{}", e.getMessage());
        ExceptionType exceptionType = ExceptionType.BAD_REQUEST;

        ErrorResponse errorResponse = new ErrorResponse(exceptionType);

        return ResponseEntity
                .status(exceptionType.getHttpStatus())
                .body(errorResponse);
    }

}