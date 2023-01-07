package com.done.ecommerce.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BaseDto {
    private LocalDate createDt;
    private String createUsrId;
}
