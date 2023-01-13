package com.done.ecommerce.service;

import com.done.ecommerce.domain.repository.UserRepository;
import com.done.ecommerce.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Long loginUser(UserDto reqDto){
        return userRepository.selectLoginUser(reqDto.getUserId(), reqDto.getUserPwd());
    }
}
