package com.done.ecommerce.config.auth;

import com.done.ecommerce.config.auth.dto.OAuthAttributes;
import com.done.ecommerce.users.domain.entity.Users;
import com.done.ecommerce.users.domain.repository.UserRepository;
import com.done.ecommerce.users.dto.Role;
import com.done.ecommerce.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession session;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();


        // OAuthAttribute Service에서 가져온 Oauth2User의 attribute를 담은 클래스
        // 소셜 로그인에 활용
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        Users users = saveOrUpdate(attributes);

        // 로그인한 세션 정보를 저장
        SessionUtil.setLoginUserName(session, users.getName());
        SessionUtil.setLoginUserId(session, users.getUserId());
        SessionUtil.setLoginUserRole(session, users.getRole());
//        sessionUtil.saveSession(users);


        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(Role.findRole(users.getRole())))
                    ,attributes.getAttributes()
                    ,attributes.getNameAttributeKey());
    }

    private Users saveOrUpdate(OAuthAttributes attributes){
        Users user = userRepository.findByUserId(attributes.getUserId()).orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}
