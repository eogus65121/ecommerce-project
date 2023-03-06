package com.done.ecommerce.products.dto;

import com.done.ecommerce.products.domain.entity.Category;
import com.done.ecommerce.products.domain.entity.Products;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter @Setter
@Builder
public class ProductsAddDto{
    @NotEmpty
    private String name;
    private String description;
    @NotEmpty
    private int price;
    @NotEmpty
    private Category category;
    private String remark;
    private String createUsrId;
    private LocalDate createdDt;


    // dto > entity 변환
    public Products toEntity(){
        return Products.builder()
                .name(name)
                .description(description)
                .price(price)
                .category(category)
                .remark(remark)
                .createUsrId(createUsrId)
                .createdDt(createdDt)
                .build();
    }
}
