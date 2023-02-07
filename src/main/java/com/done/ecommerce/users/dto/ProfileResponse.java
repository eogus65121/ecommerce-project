package com.done.ecommerce.users.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class ProfileResponse {

    private String name;
    private String phone;
    private String roleNm;

}
