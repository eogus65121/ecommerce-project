package com.done.ecommerce.domain.repository;

import com.done.ecommerce.domain.entity.Users;
import com.done.ecommerce.dto.users.SaveUserDto;
import com.done.ecommerce.dto.users.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    // 모든 테스트 이전 테스트 데이터 셋팅
    @BeforeEach
    public void saveData(){
        //given
        for(int i = 1; i < 11; i++){
            userRepository.save(Users.builder()
                    .name("name" + i)
                    .userId("id" + i)
                    .userPwd("pwd" + i)
                    .phone("01000000000")
                    .role(100)
                    .build());
        }
    }

    @Test
    public void 로그인_조회(){
        // given
        String id = "id3";
        String pwd = "pwd3";

        // when
        UserDto response = userRepository.findByUserIdAndUserPwd(id, pwd);

        // then
        assertThat(response.getUserPwd()).isEqualTo(pwd);
        assertThat(response.getUserId()).isEqualTo(id);
        assertThat(response.getName()).isEqualTo("name3");
        assertThat(response.getRole()).isEqualTo(100);
    }

    @Test
    public void 아이디_중복확인(){
        //given
        String id1 = "id3";
        String id2 = "id12";

        //when
        Boolean flagTrue = userRepository.existsByUserId(id1);
        Boolean flagFalse = userRepository.existsByUserId(id2);

        // then
        // 존재 테스트
        assertThat(flagTrue).isEqualTo(true);
        // 미존재 테스트
        assertThat(flagFalse).isEqualTo(false);
    }

    @Test
    public void 신규사용자_저장(){
        //given
        SaveUserDto saveUserDto = new SaveUserDto();
        saveUserDto.setUserId("newId");
        saveUserDto.setUserPwd("newPwd");
        saveUserDto.setName("newName");
        saveUserDto.setPhone("11111111111");
        saveUserDto.setRole(300);

        //when
        List<Users> list1 = userRepository.findAll();
        int beforeCnt = list1.size();
        userRepository.save(saveUserDto.toEntity(saveUserDto.getName(), saveUserDto.getUserId(), saveUserDto.getUserPwd(), saveUserDto.getPhone(), saveUserDto.getRole()));
        List<Users> list2 = userRepository.findAll();
        int afterCnt = list2.size();

        //then
        assertThat(afterCnt).isEqualTo(beforeCnt+1);
    }

    @Test
    public void 비밀번호_변경(){
        //given
        String id = "id2";
        String afterPwd = "pwd33";

        //when
        userRepository.updateUserPwdByUserId(id, afterPwd);
        UserDto userDto = userRepository.findByUserIdAndUserPwd(id, afterPwd);

        //then
        assertThat(userDto.getUserPwd()).isEqualTo(afterPwd);

    }
}
