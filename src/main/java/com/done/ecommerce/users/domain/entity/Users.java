package com.done.ecommerce.users.domain.entity;

import com.done.ecommerce.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userPwd;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private int role;

    @Builder
    public Users(String name, String userId, String userPwd, String phone, int role){
        this.name = name;
        this.userId = userId;
        this.userPwd = userPwd;
        this.phone = phone;
        this.role = role;
    }
}
