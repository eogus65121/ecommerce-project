package com.done.ecommerce.users.domain.repository;

import com.done.ecommerce.users.domain.entity.Users;
import com.done.ecommerce.users.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{

    // id와 pwd 일치 여부로 로그인 check
    public UserDto findByUserIdAndUserPwd(String userId, String userPwd);

    public Optional<Users> findByUserId(String userId);

    public boolean existsByUserId(String userId);

    @Modifying
    @Transactional
    @Query(value = "update Users u set u.userPwd = :userPwd where u.userId = :userId")
    public void updateUserPwdByUserId(@Param(value = "userId") String userId, @Param(value = "userPwd") String userPwd);

}
