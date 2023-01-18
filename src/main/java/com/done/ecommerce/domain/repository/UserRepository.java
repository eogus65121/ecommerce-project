package com.done.ecommerce.domain.repository;

import com.done.ecommerce.domain.entity.Users;
import com.done.ecommerce.dto.users.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{

    UserDto findByIdAndPwd(String id, String pwd);

}
