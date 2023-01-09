package com.done.ecommerce.controller;

import com.done.ecommerce.domain.entity.Products;
import com.done.ecommerce.dto.products.ProductsAddDto;
import com.done.ecommerce.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="products")
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

    /**
     * 전체 상품 조회
     * @return
     */
    @GetMapping(value = "/select-products")
    public ResponseEntity<List<Products>> selectProducts(){
        List<Products> rtnList = productsService.selectAllProducts();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

}
