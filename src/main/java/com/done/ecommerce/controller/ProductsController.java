package com.done.ecommerce.controller;

import com.done.ecommerce.dto.products.ProductIdProjectionInterface;
import com.done.ecommerce.dto.products.ProductsAddDto;
import com.done.ecommerce.dto.products.ProductsDto;
import com.done.ecommerce.service.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value="products")
public class ProductsController {

    private final ProductsService productsService;

    /**
     * 전체 상품 조회 캐싱 적용
     * @return
     */
    @GetMapping(value = "/select-products")
    public List<ProductsDto> selectProducts() {
        return productsService.selectAllProducts();
    }

    /**
     * 특정 상품 조회
     */
    @GetMapping(value="/productDetl/{id}")
    public ResponseEntity<ProductIdProjectionInterface> selectProductDetl(@PathVariable("id") Long id) {
        ProductIdProjectionInterface response = productsService.selectProductDetl(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * 상품 신규 추가
     * @param requestDto
     * @return
     */
    @PostMapping(value = "/add-product")
    public HttpStatus addProduct(@RequestBody ProductsAddDto requestDto) {
        try{
            productsService.saveNewProduct(requestDto);
        }catch (Exception e){
            e.printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.CREATED;
    }

    /**
     * 상품 삭제
     * @param
     */
    @DeleteMapping HttpStatus deleteProduct(@RequestParam("id") Long id) {
        try{
            productsService.deleteProduct(id);
        }catch(Exception e){
            e.printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }

}
