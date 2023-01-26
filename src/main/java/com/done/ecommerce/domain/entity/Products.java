package com.done.ecommerce.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter                 // entity에는 @setter를 추가하지 않는다
@NoArgsConstructor      // 기본 생성자 자동 추가
@AllArgsConstructor
@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // auto increment
    private Long id;

    @Column(length = 500, nullable = false)
    private String name;        // 상품 이름

    @Column(length = 3000, nullable = false)
    private String description;     // 상품 설명

    @Column(nullable = false)
    private int price;      // 금액

    @Column(nullable = false)
    private LocalDate createdDt;        // 등록일자z

    @Column(nullable = false)
    private String createUsrId;         // 등록자

    private int groupId;        // 상품 그룹 코드

    private String remark;          // 비고

    @Builder
    public Products(String name, String description, int price, LocalDate createdDt, String createUsrId, int groupId, String remark){
        this.name = name;
        this.description = description;
        this.price = price;
        this.createdDt = createdDt;
        this.createUsrId = createUsrId;
        this.groupId = groupId;
        this.remark = remark;
    }

}
