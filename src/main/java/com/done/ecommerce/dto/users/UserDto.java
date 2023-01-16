package com.done.ecommerce.dto.users;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@Builder
@ToString
public class UserDto {
    private Long id;
    private String name;
    private String userId;
    private String userPwd;
    private int age;
    private String phone;
    private String role;
}
