package com.done.ecommerce.users.dto;

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
