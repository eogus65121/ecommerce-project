package com.done.ecommerce.utils;

import com.done.ecommerce.domain.entity.Users;
import com.done.ecommerce.dto.users.Role;
import org.hibernate.Session;

import javax.servlet.http.HttpSession;

public class SessionUtil {
    private static String LOGIN_USER_NAME = "LOGIN_USER_NAME";
    private static String LOGIN_USER_ID = "LOGIN_USER_ID";
    private static String LOGIN_USER_PWD = "LOGIN_USER_PWD";
    private static String LOGIN_USER_ROLE = "LOGIN_USER_ROLE";

    // 인스턴스화 방지
    private SessionUtil(){}

    public void saveSession(Users user){
        this.LOGIN_USER_ID = user.getUser_id();
        this.LOGIN_USER_NAME = user.getName();
        this.LOGIN_USER_PWD = user.getUser_pwd();
        this.LOGIN_USER_ROLE = Role.findRole(user.getRole());
    }

    public static String getLoginUserName(HttpSession session) {return session.getAttribute(LOGIN_USER_NAME).toString();}

    public static void setLoginUserName(HttpSession session, String name){
        session.setAttribute(LOGIN_USER_NAME, name);
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

}
