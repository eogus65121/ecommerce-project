package com.done.ecommerce.common.annotation;

import java.lang.annotation.*;

/**
 * 로그인이 된 사용자만 접근 가능한 URL에 대한 확인
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginRequired {
}
