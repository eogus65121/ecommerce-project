package com.done.ecommerce.config;

import com.done.ecommerce.config.auth.CustomOAuth2UserService;
import com.done.ecommerce.dto.users.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity      // Spring Security 설정 활성화
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션을 disabled
                .and()
                .authorizeRequests() // URL별 권한 관리를 설정하는 옵션의 지작점
                .antMatchers("/", "/users/login", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()  // 권한 관리 대상 지정 옵션, 해당 URL들은 모두 허용
                .antMatchers("/products/**").hasAnyRole(Role.CUSTOMER.name(), Role.ADMIN.name(), Role.EMPLOYEE.name())  // 해당 URL들은 특정 사용자만 허용
                .anyRequest().authenticated()   // 설정된 값들 이외에 나머지 URL에 대한 설정, authenticated() = 인증된 사용자들에게만 허용(로그인이 된 사용자)
                .and()
                    .logout()
                        .logoutSuccessUrl("/index") // 로그아웃 성공 시 홈 화면으로 이동
                .and()
                .oauth2Login()  // OAuth2 로그인 기능에 대한 설정의 진입점
                .userInfoEndpoint()// OAuth2 로그인 이후 사용자 정보를 가져올 때의 설정들을 담당
                .userService(customOAuth2UserService);  // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록

        // 테스트를 위한 security 설정
//        http.ignoring()
//                .antMatchers("**");
    }

}
