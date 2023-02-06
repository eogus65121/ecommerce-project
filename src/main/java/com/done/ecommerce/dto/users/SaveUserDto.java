package com.done.ecommerce.dto.users;

import com.done.ecommerce.domain.entity.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class SaveUserDto {
    @NotBlank
    private String name;
    @NotBlank
    private String userId;
    @NotBlank
    private String userPwd;
    @NotBlank
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
