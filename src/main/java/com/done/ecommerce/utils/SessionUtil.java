package com.done.ecommerce.utils;

import com.done.ecommerce.dto.users.Role;

import javax.servlet.http.HttpSession;

public class SessionUtil {
    private static final String LOGIN_USER_ID = "LOGIN_USER_ID";
    private static final String LOGIN_USER_ROLE = "LOGIN_USER_ROLE";

    // 인스턴스화 방지
    private SessionUtil(){}

    public static String getLoginUserId(HttpSession session){
        return session.getAttribute(LOGIN_USER_ID).toString();
    }

    public static void setLoginUserId(HttpSession session, String id){
        session.setAttribute(LOGIN_USER_ID, id);
    }

    public static String getLoginUserRole(HttpSession session){
        return session.getAttribute(LOGIN_USER_ROLE).toString();
    }

    public static void setLoginUserRole(HttpSession session, int role){
        session.setAttribute(LOGIN_USER_ROLE, Role.findRole(role));
    }

}
