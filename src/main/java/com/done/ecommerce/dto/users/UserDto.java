package com.done.ecommerce.dto.users;

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
}
