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
    private String user_id;

    @Column(nullable = false)
    private String user_pwd;

    private int age;

    private String phone;

    @Column(nullable = false)
    private int role;

    @Builder
    public Users(String name, String user_id, String user_pwd, int role){
        this.name = name;
        this.user_id = user_id;
        this.user_pwd = user_pwd;
        this.role = role;
    }
}
