package com.done.ecommerce.dto.user;

public enum Role {
    ADMIN("관리자", 100)
    ,EMPL("직원", 200)
    ,CUST("고객", 300);

    private String roleName;
    private int code;

    Role(String roleName, int code){
        this.roleName = roleName;
        this.code = code;
    }
}
