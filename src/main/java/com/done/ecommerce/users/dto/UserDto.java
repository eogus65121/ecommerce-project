package com.done.ecommerce.users.dto;

import com.done.ecommerce.users.domain.entity.Users;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter @Setter
@Builder
@ToString
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String userId;
    @NotBlank
    private String userPwd;
    private String phone;
    private int role;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Users toEntity(String name, String userId, String userPwd, int role){
        return Users.builder()
                .name(name)
                .userId(userId)
                .userPwd(userPwd)
                .role(role)
                .build();
    }
}
