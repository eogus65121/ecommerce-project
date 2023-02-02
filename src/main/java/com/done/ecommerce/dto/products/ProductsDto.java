package com.done.ecommerce.dto.products;

가
import com.done.ecommerce.domain.entity.Products;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect
public class ProductsDto {
    private Long id;
    private String name;
    private String description;
    private int price;
    private LocalDate createdDt;
    private String createUsrId;
    private int groupId;
    private String remark;
}
