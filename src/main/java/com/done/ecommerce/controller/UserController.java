package com.done.ecommerce.controller;

import com.done.ecommerce.dto.user.UserDto;
import com.done.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="user")
public class UserController {

    private final UserService userService;

    /**
     * 사용자 로그인
     * @param reqDto
     */
    @GetMapping(value="/loginView")
    public ResponseEntity<?> loginUser(@RequestBody UserDto reqDto){
        if(userService.loginUser(reqDto) == 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/index"));  // main 화면

        // 로그인 성공 시 header의 setLocation으로 리다이렉트
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}
