package com.done.ecommerce.dto.products;

import java.time.LocalDate;

public interface ProductIdProjectionInterface {
    Long getIdx();
    String getName();
    String getDescription();
    int getPrice();
    LocalDate getCreatedDt();
    int getGroupId();
    String getRemark();
}

