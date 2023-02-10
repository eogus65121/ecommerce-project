package com.done.ecommerce.config;

import com.done.ecommerce.common.interceptor.LoginInterceptor;
import com.done.ecommerce.common.resolver.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    // loginRequired custom annotation - 인터셉터 등록
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginInterceptor);
    }

    // loginUserArgumentResolver - 리졸버 등록
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolverList){
        resolverList.add(loginUserArgumentResolver);
    }


}
