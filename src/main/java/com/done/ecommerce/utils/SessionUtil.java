package com.done.ecommerce.utils;

import com.done.ecommerce.dto.users.Role;
import com.done.ecommerce.dto.users.UserDto;

import javax.servlet.http.HttpSession;

public class SessionUtil {
    private static final String LOGIN_USER_NAME = "LOGIN_USER_NAME";
    private static final String LOGIN_USER_ID = "LOGIN_USER_ID";
    private static final String LOGIN_USER_ROLE = "LOGIN_USER_ROLE";

    // 인스턴스화 방지
    private SessionUtil(){}

    public static void setLoginUserInfo(HttpSession session, UserDto userDto){
        SessionUtil.setLoginUserRole(session, userDto.getRole());
        SessionUtil.setLoginUserName(session, userDto.getName());
        SessionUtil.setLoginUserId(session, userDto.getUserId());
    }

    public static String getLoginUserName(HttpSession session){
        return session.getAttribute(LOGIN_USER_ID).toString();
    }

    public static void setLoginUserName(HttpSession session, String name){
        session.setAttribute(LOGIN_USER_ID, name);
    }


    public static String getLoginUserId(HttpSession session){
        return session.getAttribute(LOGIN_USER_ID).toString();
    }

    public static void setLoginUserId(HttpSession session, String id){
        session.setAttribute(LOGIN_USER_ID, id);
    }

    public static String getLoginUserRole(HttpSession session){
        return session.getAttribute(LOGIN_USER_ROLE).toString();
    }

    // Enum을 사용하여 로그인 사용자의 Role을 세션에 저장
    public static void setLoginUserRole(HttpSession session, int role){
        session.setAttribute(LOGIN_USER_ROLE, Role.findRole(role));
    }

    public static void sessionClear(HttpSession session){
        session.invalidate();
    }

}

