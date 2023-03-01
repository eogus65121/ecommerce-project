package com.done.ecommerce.products.controller;

import com.done.ecommerce.products.dto.ProductIdProjectionInterface;
import com.done.ecommerce.products.dto.ProductsAddDto;
import com.done.ecommerce.products.dto.ProductsDto;
import com.done.ecommerce.products.service.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public HttpStatus addProduct(@RequestBody @Valid ProductsAddDto requestDto) {
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

    /**
     * 상품 그룹코드(카테고리별) 조회하기
     */
    @GetMapping(value = "/{category}/select-products")
    public ResponseEntity<List<ProductIdProjectionInterface>> selectProductsBycategory(@PathVariable("category") int category){
        List<ProductIdProjectionInterface> rtnList = productsService.findByCategory(category);
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    /**
     * 상품 명으로 품목 조회
     */
    @GetMapping(value = "/select-products/{name}")
    public ResponseEntity<List<ProductIdProjectionInterface>> selectProductsByName(@PathVariable("name") String name){
        List<ProductIdProjectionInterface> rtnList = productsService.selectProductByName(name);
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

}
