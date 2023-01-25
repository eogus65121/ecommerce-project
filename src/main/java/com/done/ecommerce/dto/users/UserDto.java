package com.done.ecommerce.dto.users;

import com.done.ecommerce.domain.entity.Users;
import lombok.*;

@Getter @Setter
@Builder
@ToString
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String userId;
    private String userPwd;
    private int age;
    private String phone;
    private int role;

    public Users toEntity(String name, String userId, String userPwd, int role){
        return Users.builder()
                .name(name)
                .userId(userId)
                .userPwd(userPwd)
                .role(role)
                .build();
    }
}
