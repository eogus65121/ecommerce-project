package com.done.ecommerce.dto.products;

import com.done.ecommerce.domain.entity.Products;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
public class ProductsAddDto{
    private Long id;
    private String name;
    private String description;
    private int price;
    private int productGroupId;
    private String remark;
    private String createUsrId;
    private LocalDate createdDt;


    // dto > entity 변환
    public Products toEntity(){
        return Products.builder()
                .name(name)
                .description(description)
                .price(price)
                .productGroupId(productGroupId)
                .remark(remark)
                .createUsrId(createUsrId)
                .createdDt(createdDt)
                .build();
    }
}
