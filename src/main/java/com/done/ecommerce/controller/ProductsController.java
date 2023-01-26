package com.done.ecommerce.controller;

import com.done.ecommerce.domain.entity.Products;
import com.done.ecommerce.dto.products.ProductIdProjectionInterface;
import com.done.ecommerce.dto.products.ProductsAddDto;
import com.done.ecommerce.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="products")
public class ProductsController {

    private final ProductsService productsService;

    /**
     * h2 db 테스트를 위한 데이터 셋팅
     */
    @GetMapping("/setProduct")
    public HttpStatus setData(){
        for(int i = 1; i < 100; i++){
            productsService.productDataSet(Products.builder()
                    .name("name" + i)
                    .description("description" + i)
                    .price(10000 + i)
                    .createdDt(LocalDate.now())
                    .createUsrId("admin" + i)
                    .groupId(1)
                    .remark("remark" + i)
                    .build());
        }
        return HttpStatus.OK;
    }

    /**
     * 전체 상품 조회
     * @return
     */
    @GetMapping(value = "/select-products")
    public ResponseEntity<List<Products>> selectProducts() throws Exception{
        List<Products> rtnList = productsService.selectAllProducts();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    /**
     * 특정 상품 조회
     */
    @GetMapping(value="/productDetl/{id}")
    public ResponseEntity<ProductIdProjectionInterface> selectProductDetl(@PathVariable("id") Long id) throws Exception{
        ProductIdProjectionInterface response = productsService.selectProductDetl(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * 상품 신규 추가
     * @param requestDto
     * @return
     */
    @PostMapping(value = "/add-product")
    public HttpStatus addProduct(@RequestBody ProductsAddDto requestDto) throws Exception{
        try{
            productsService.saveNewProduct(requestDto);
        }catch (Exception e){
            e.printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }

    /**
     * 상품 삭제
     * @param
     */
    @DeleteMapping HttpStatus deleteProduct(@RequestParam("id") Long id) throws Exception{
        try{
            productsService.deleteProduct(id);
        }catch(Exception e){
            e.printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }

}
