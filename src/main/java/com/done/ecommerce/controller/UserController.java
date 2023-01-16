package com.done.ecommerce.controller;

import com.done.ecommerce.dto.users.LoginReq;
import com.done.ecommerce.service.UserService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(value="users")
public class UserController {

    private final UserService userService;

    /**
     * 사용자 로그인
     * @param reqDto
     */
    @PostMapping(value="/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginReq reqDto){
        if(userService.loginUser(reqDto) == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/home"));  // main 화면

        // 로그인 성공 시 header의 setLocation으로 리다이렉트
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}
