package com.done.ecommerce.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BaseDto {
    private LocalDate createDt;     // 생성 날짜
    private String createUsrId;     // 생성 user id
}
