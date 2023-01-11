package com.done.ecommerce.dto.products;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter
@ToString
public class ProductsReqDto {
    private Long id;
    private String name;
    private String description;
    private int price;
    private LocalDate createdDt;
    private int groupId;
}
