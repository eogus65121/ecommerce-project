package com.done.ecommerce.dto.products;

import com.done.ecommerce.dto.BaseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ProductsAddDto extends BaseDto {
    private Long id;
    private String name;
    private String description;
    private int price;
    private int productGroupId;
    private String remark;
}
