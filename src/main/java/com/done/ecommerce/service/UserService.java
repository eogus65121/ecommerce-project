package com.done.ecommerce.service;

import com.done.ecommerce.domain.repository.UserRepository;
import com.done.ecommerce.dto.users.LoginReq;
import com.done.ecommerce.dto.users.SaveUserDto;
import com.done.ecommerce.dto.users.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserDto loginUser(LoginReq reqDto) {
        return userRepository.findByUserIdAndUserPwd(reqDto.getUserId(), reqDto.getUserPwd());
    }

    // user id 중복확인
    public boolean isDuplicatedUserId(String userId){
        return userRepository.existsByUserId(userId);
    }

    // 신규 회원 등록
    @Transactional
    public void saveUser(SaveUserDto saveUserDto){
        userRepository.save(saveUserDto.toEntity(saveUserDto.getName(), saveUserDto.getUserId(), saveUserDto.getUserPwd(), saveUserDto.getPhone(), saveUserDto.getRole()));
    }

    // 사용자 pwd 변경
    public void updateUserPwdByUserId(LoginReq loginReq){
        userRepository.updateUserPwdByUserId(loginReq.getUserId(), loginReq.getUserPwd());
    }
}
