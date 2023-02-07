package com.done.ecommerce.dto.products;

import com.done.ecommerce.domain.entity.Products;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
                .groupId(productGroupId)
                .remark(remark)
                .createUsrId(createUsrId)
                .createdDt(createdDt)
                .build();
    }
}
