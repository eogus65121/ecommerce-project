package com.done.ecommerce.dto.users;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginReq {
    @NotBlank
    private String userId;
    @NotBlank
    private String userPwd;
}
