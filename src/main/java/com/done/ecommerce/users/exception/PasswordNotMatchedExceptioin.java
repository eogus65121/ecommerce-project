package com.done.ecommerce.users.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PasswordNotMatchedExceptioin extends RuntimeException{

    public PasswordNotMatchedExceptioin(String message){ super(message);}

    public PasswordNotMatchedExceptioin(String message, Throwable cause) { super(message, cause); }

}
