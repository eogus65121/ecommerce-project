package com.done.ecommerce.dto.users;

import com.done.ecommerce.domain.entity.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
public class ProfileResponse {

    private String name;
    private String phone;
    private String roleNm;

}
