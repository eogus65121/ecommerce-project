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
    private String name;    // 이름

    @Column(nullable = false)
    private String userId;  // ID

    @Column(nullable = false)
    private String userPwd; // PWD

    @Column(nullable = false)
    private String phone;   // 휴대폰 번호

    @Column(nullable = false)
    private int role;       // 역할

    @Embedded
    private Address address;    // 사용자 주소

    @Builder
    public Users(String name, String userId, String userPwd, String phone, int role, Address address){
        this.name = name;
        this.userId = userId;
        this.userPwd = userPwd;
        this.phone = phone;
        this.role = role;
        this.address = address;
    }

    // 주소 업데이트
    public void setAddress(AddressRequest addressRequest){
        this.address = addressRequest.toAddress();
    }
}
