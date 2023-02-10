package com.done.ecommerce.users.service;

import com.done.ecommerce.users.domain.entity.Users;
import com.done.ecommerce.users.domain.repository.UserRepository;
import com.done.ecommerce.users.dto.LoginReq;
import com.done.ecommerce.users.dto.SaveUserDto;
import com.done.ecommerce.users.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

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
    public void updateUserPwdByUserId(String userId, String userPwd){
        userRepository.updateUserPwdByUserId(userId, userPwd);
    }

    // userId로 사용자 찾기 > 결과값 DTO로 매핑
    public UserDto findByUserId(String userId){
        Users user = userRepository.findByUserId(userId).orElseThrow(NoSuchElementException::new);
        return modelMapper.map(user, UserDto.class);
    }
}