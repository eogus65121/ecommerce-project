package com.done.ecommerce.domain.repository;

import com.done.ecommerce.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{

    // login user check
    @Query(value = "select count(us.id) from Users AS us where us.user_id = :userId and us.user_pwd = :userPwd")
    Long selectLoginUser(@Param(value = "userId") String userId, @Param(value = "userPwd") String userPwd);

}
