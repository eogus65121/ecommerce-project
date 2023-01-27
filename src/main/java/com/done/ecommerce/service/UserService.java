package com.done.ecommerce.service;

import com.done.ecommerce.domain.repository.UserRepository;
import com.done.ecommerce.dto.users.LoginReq;
import com.done.ecommerce.dto.users.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserDto loginUser(LoginReq reqDto) {
        return userRepository.findByUserIdAndUserPwd(reqDto.getUserId(), reqDto.getUserPwd());
    }
}
