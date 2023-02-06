package com.done.ecommerce.dto.users;

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
