package com.done.ecommerce.users.dto;

import java.util.Arrays;

public enum Role {
    ADMIN( 100)
    ,EMPLOYEE( 200)
    ,CUSTOMER( 300)
    ,EMPTY(0)
    ;

    private int code;

    Role(int code){this.code = code;}

    // role 코드를 사용하여 role name 출력
    public static String findRole(int reqCode){
        return Arrays.stream(Role.values())
                .filter(o -> o.code == reqCode)
                .findAny().get().name();
    }
}
