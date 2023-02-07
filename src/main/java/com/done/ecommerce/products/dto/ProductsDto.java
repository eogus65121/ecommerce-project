package com.done.ecommerce.products.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private int price;
    private LocalDate createdDt;
    private String createUsrId;
    private int groupId;
    private String remark;
}
