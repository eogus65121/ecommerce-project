package com.done.ecommerce.users.controller;

import com.done.ecommerce.common.annotation.LoginRequired;
import com.done.ecommerce.common.annotation.LoginUser;
import com.done.ecommerce.users.domain.entity.Users;
import com.done.ecommerce.users.dto.AddressRequest;
import com.done.ecommerce.users.dto.LoginReq;
import com.done.ecommerce.users.dto.SaveUserDto;
import com.done.ecommerce.users.dto.UserDto;
import com.done.ecommerce.users.service.UserService;
import com.done.ecommerce.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginReq reqDto, HttpSession session){
        UserDto userDto = userService.loginUser(reqDto);
        // 로그인 실패
        if(userDto == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 로그인 성공
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/index"));  // main 화면

        // 세션에 id, role 저장
        SessionUtil.setLoginUserInfo(session, userDto);

        // 로그인 성공 시 header의 setLocation으로 리다이렉트
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    /**
     * 로그인 사용자 정보 조회
     */
    @LoginRequired
    @GetMapping(value="/my-profile")
    public ResponseEntity<UserDto> getUserProfile(@LoginUser UserDto profileResponse) throws AuthenticationException{
        return new ResponseEntity<>(profileResponse, HttpStatus.OK);
    }

    @LoginRequired
    @PutMapping(value="/my-profile")
    public HttpStatus setUserAddress(@LoginUser Users users, @RequestBody AddressRequest addressRequest){

        userService.setUserAddress(users, addressRequest);
        return HttpStatus.OK;
    }


    /**
     * 비밀번호 변경
     */
    @LoginRequired
    @PutMapping(value="/password")
    public HttpStatus updateUserPwdByUserId(@LoginUser UserDto profileResponse){
        userService.updateUserPwdByUserId(profileResponse.getUserId(), profileResponse.getUserPwd());
        return HttpStatus.OK;
    }

    /**}
     * 회원가입
     */
    @PostMapping(value="/save-user")
    public HttpStatus saveUser(@RequestBody @Valid SaveUserDto saveUserDto){

        boolean isDuplicated = userService.isDuplicatedUserId(saveUserDto.getUserId());
        if(isDuplicated) return HttpStatus.CONFLICT;

        saveUserDto.setRole(300);   // 일반 신규 회원 등록
        userService.saveUser(saveUserDto);

        return HttpStatus.CREATED;
    }

    /**
     * 사용자 아이디 중복확인
     */
    @GetMapping(value="/duplicated/{userId}")
    public HttpStatus isDuplicatedUserId(@PathVariable String userId){
        boolean isDuplicated = userService.isDuplicatedUserId(userId);
        if(isDuplicated) return HttpStatus.CONFLICT;

        return HttpStatus.OK;
    }

    /**
     * 로그아웃
     */
    @LoginRequired
//    @CacheEvict(value="LOGOUT_DELETE_CAHCE")
    @PostMapping (value="/logout")
    public HttpStatus logout(HttpServletRequest request, HttpSession session){
        // 로그인 Session이 없는 경우 예외처리
        if(session == null || !request.isRequestedSessionIdValid()) {
            return HttpStatus.UNAUTHORIZED;
        }

        SessionUtil.sessionClear(session);
        // 로그아웃 시 홈 가화면으로 이동
        return HttpStatus.MOVED_PERMANENTLY;
    }

}
