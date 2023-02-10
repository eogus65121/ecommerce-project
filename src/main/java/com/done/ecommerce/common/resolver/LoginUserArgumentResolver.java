package com.done.ecommerce.common.resolver;

import com.done.ecommerce.common.annotation.LoginUser;
import com.done.ecommerce.users.service.UserService;
import com.done.ecommerce.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

/**
 * supportsParameter : parameter를 resolver에 지원 여부 확인 true/false
 * true = resolveArgument 반환
 *
 * hasParameterAnnotation : 해당 매소드에 LoginUser 어노테이션이 존재하는지 확인
 *
 * resolveArgument : 실제 바인딩할 객체 반환
 */


@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserService userService;
    private final HttpSession session;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter){
        return methodParameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer
    , NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception{
        return userService.findByUserId(SessionUtil.getLoginUserId(session));
    }
}
