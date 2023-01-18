package com.done.ecommerce.domain.repository;

import com.done.ecommerce.domain.entity.Users;
import com.done.ecommerce.dto.users.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

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
        for(int i = 1; i < 11; i++){
            userRepository.save(Users.builder()
                    .name("name" + i)
                    .id("id" + i)
                    .pwd("pwd" + i)
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
        UserDto response = userRepository.findByIdAndPwd(id, pwd);

        // then
        assertThat(response.getPwd()).isEqualTo(pwd);
        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getName()).isEqualTo("name3");
        assertThat(response.getRole()).isEqualTo(100);
    }
}
