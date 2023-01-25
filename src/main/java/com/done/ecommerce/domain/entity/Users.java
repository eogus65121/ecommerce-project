package com.done.ecommerce.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userPwd;

    private int age;

    private String phone;

    @Column(nullable = false)
    private int role;

    @Builder
    public Users(String name, String userId, String userPwd, int role){
        this.name = name;
        this.userId = userId;
        this.userPwd = userPwd;
        this.role = role;
    }
}
