package com.done.ecommerce.dto.users;

import lombok.*;

@Getter @Setter
@Builder
@ToString
@AllArgsConstructor
public class UserDto {
    private Long idx;
    private String name;
    private String id;
    private String pwd;
    private int age;
    private String phone;
    private int role;
}
