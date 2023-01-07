package com.done.ecommerce.controller;

import com.done.ecommerce.domain.repository.ProductsRepository;
import com.done.ecommerce.dto.products.ProductsAddDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@RequiredArgsConstructor
@RestController("/products")
public class ProductsController {

    private final ProductsRepository productsRepository;

    /**
     * 상품 신규 추가
     * @param productsAddDto
     * @return
     */
    @PostMapping(value = "/add-product")
    public HttpStatus addProduct(HttpServletRequest request, @RequestBody ProductsAddDto productsAddDto){
        // 세션을 활용한 사용자 데이터 등록 예정
        productsAddDto.setCreateUsrId("admin");
        productsAddDto.setCreateDt(LocalDate.of(2022,01,07));
        try{
            productsRepository.addProduct(productsAddDto);
        }catch (Exception e){
            e.printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }

}
