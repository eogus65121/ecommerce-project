package com.done.ecommerce.common.interceptor;

import com.done.ecommerce.common.annotation.LoginRequired;
import com.done.ecommerce.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.thymeleaf.util.StringUtils;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * HandlerMethod : 실행될 핸들러(컨트롤러의 메소드)
 * LoginRequired == null인 경우 접근 불가
 *
 * Handler 종류
 * preHandler() : 컨트롤러 메서드(핸들러) 실행 전
 * postHandler() : 컨트롤러 메서드(핸들러) 실행 직 후
 * afterCompletion() : view 페이지가 렌더링 된 후
 */

@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final HttpSession session;

    // 로그인 확인을 위한 인터펩터, LoginRequired Custom Annotation
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{

        if(handler instanceof HandlerMethod && ((HandlerMethod) handler).hasMethodAnnotation(LoginRequired.class)){
            String userId = SessionUtil.getLoginUserId(session);

            if(StringUtils.isEmpty(userId)){
                throw new AuthenticationException("로그인 이후 사용이 가능합니다.");
            }
        }

        return true;
    }

}
