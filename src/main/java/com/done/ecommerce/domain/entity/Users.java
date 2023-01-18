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
    private Long idx;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String pwd;

    private int age;

    private String phone;

    @Column(nullable = false)
    private int role;

    @Builder
    public Users(String name, String id, String pwd, int role){
        this.name = name;
        this.id = id;
        this.pwd = pwd;
        this.role = role;
    }
}
