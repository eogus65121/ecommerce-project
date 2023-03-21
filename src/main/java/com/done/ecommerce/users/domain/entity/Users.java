package com.done.ecommerce.users.domain.entity;

import com.done.ecommerce.common.domain.BaseTimeEntity;
import com.done.ecommerce.users.dto.AddressRequest;
import com.done.ecommerce.users.domain.entity.Address;
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

    @Embedded
    private Address address;

    @Builder
    public Users(String name, String userId, String userPwd, String phone, int role, Address address){
        this.name = name;
        this.userId = userId;
        this.userPwd = userPwd;
        this.phone = phone;
        this.role = role;
        this.address = address;
    }

    public void setAddress(AddressRequest addressRequest){
        this.address = addressRequest.toAddress();
    }
}
