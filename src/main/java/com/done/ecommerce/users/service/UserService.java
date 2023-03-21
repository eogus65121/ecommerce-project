package com.done.ecommerce.users.service;

import com.done.ecommerce.users.domain.entity.Users;
import com.done.ecommerce.users.dto.AddressRequest;
import com.done.ecommerce.users.dto.LoginReq;
import com.done.ecommerce.users.dto.SaveUserDto;
import com.done.ecommerce.users.dto.UserDto;

public interface UserService {

    // 로그인
    public UserDto loginUser(LoginReq reqDto);
    // user id 중복확인
    public boolean isDuplicatedUserId(String userId);
    // 신규 회원 등록
    public void saveUser(SaveUserDto saveUserDto);
    // 사용자 pwd 변경
    public void updateUserPwdByUserId(String userId, String userPwd);
    // id로 사용자 찾기
    public UserDto findByUserId(String userId);
    // 사용자 주소 셋팅
    public void setUserAddress(Users users, AddressRequest addressRequest);

}
