package com.done.ecommerce.controller;

import com.done.ecommerce.dto.users.LoginReq;
import com.done.ecommerce.dto.users.UserDto;
import com.done.ecommerce.service.UserService;
import com.done.ecommerce.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

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
    @GetMapping(value="/user-info")
    public ResponseEntity<Map<String, Object>> userInfo(HttpServletRequest request, HttpSession session) throws AuthenticationException{
        // 로그인 Session이 없는 경우 예외처리
        if(session == null || !request.isRequestedSessionIdValid()) {
            throw new AuthenticationException();
        }

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("name", SessionUtil.getLoginUserId(session));
        userInfo.put("role", SessionUtil.getLoginUserRole(session));
        userInfo.put("id", SessionUtil.getLoginUserId(session));

        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    /**
     * 로그아웃
     */
    @GetMapping (value="/logout")
    public HttpStatus logout(HttpSession session){
        SessionUtil.sessionClear(session);
        // 로그아웃 시 홈 가화면으로 이동
        return HttpStatus.MOVED_PERMANENTLY;
    }

}
