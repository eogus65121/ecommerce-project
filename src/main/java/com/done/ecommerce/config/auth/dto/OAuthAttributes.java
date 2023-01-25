package com.done.ecommerce.config.auth.dto;

import com.done.ecommerce.domain.entity.Users;
import com.done.ecommerce.dto.users.UserDto;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String userId;
    private String userPwd;
    private int role;

    // OAuth2User에서 변환하는 데이터는 모두 Map이기 떄문에 값 하나하나를 변환해야한다.(Google)
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .userId((String) attributes.get("id"))
                .userPwd((String) attributes.get("pwd"))
                .role((int) attributes.get("role"))
                .build();
    }

    // Users Entity를 생성, 처음 가입 시 기본 권한 이므로 Customer 권한 강제 입력
    public Users toEntity(){
        return Users.builder()
                .name(name)
                .userId(userId)
                .userPwd(userPwd)
                .role(300)
                .build();
    }

}
