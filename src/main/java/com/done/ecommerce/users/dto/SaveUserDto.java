package com.done.ecommerce.users.dto;

import com.done.ecommerce.users.domain.entity.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter @Setter
public class SaveUserDto {
    @NotBlank
    private String name;
    @NotBlank
    private String userId;
    @NotBlank
    @Pattern(message = "최소 한 개 이상의 대소문자와 숫자, 특수문자를 포함한 8자 이상 16자 이하의 비밀번호를 입력해야합니다.",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#!~$%^&-+=()])(?=\\S+$).{8,16}$")
    private String userPwd;
    @NotBlank
    @Pattern(message="전화번호는 숫자만 입력이 가능합니다.",
            regexp = "^[0-9]+$")
    private String phone;
    private int role;

    @Builder
    public Users toEntity(String name, String userId, String userPwd, String phone, int role){
        return Users.builder()
                .name(name)
                .userId(userId)
                .userPwd(userPwd)
                .phone(phone)
                .role(role)
                .build();
    }
}
