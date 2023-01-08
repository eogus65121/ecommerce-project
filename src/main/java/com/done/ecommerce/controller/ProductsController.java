package com.done.ecommerce.controller;

import com.done.ecommerce.domain.repository.ProductsRepository;
import com.done.ecommerce.dto.products.ProductsAddDto;
import com.done.ecommerce.service.ProductsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
public class ProductsController {

    private final ProductsService productsService;

    /**
     * 상품 신규 추가
     * @param requestDto
     * @return
     */
    @PostMapping(value = "/add-product")
    public HttpStatus addProduct(HttpServletRequest requestHttp, @RequestBody ProductsAddDto requestDto){
        try{
            productsService.saveNewProduct(requestDto);
        }catch (Exception e){
            e.printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }

}
