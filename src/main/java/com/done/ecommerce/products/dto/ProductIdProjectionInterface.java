package com.done.ecommerce.products.dto;

import com.done.ecommerce.products.domain.entity.Category;

import java.time.LocalDate;

public interface ProductIdProjectionInterface {
    Long getId();
    String getName();
    String getDescription();
    int getPrice();
    LocalDate getCreatedDt();
    int getCategory();
    String getRemark();
}

