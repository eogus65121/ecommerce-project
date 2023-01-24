package com.done.ecommerce.domain.repository;

import com.done.ecommerce.domain.entity.Users;
import com.done.ecommerce.dto.users.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{

    // id와 pwd 일치 여부로 로그인 check
    UserDto findByIdAndPwd(String id, String pwd);

    Optional<Users> findByUser_id(String id);

    void merge(Users user) throws Exception;
}
