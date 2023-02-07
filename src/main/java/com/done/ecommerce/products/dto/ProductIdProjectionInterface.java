package com.done.ecommerce.products.dto;

import java.time.LocalDate;

public interface ProductIdProjectionInterface {
    Long getId();
    String getName();
    String getDescription();
    int getPrice();
    LocalDate getCreatedDt();
    int getGroupId();
    String getRemark();
}

