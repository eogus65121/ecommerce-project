package com.done.ecommerce.service;

import com.done.ecommerce.domain.entity.Products;
import com.done.ecommerce.domain.repository.ProductsRepository;
import com.done.ecommerce.dto.products.ProductIdProjectionInterface;
import com.done.ecommerce.dto.products.ProductsAddDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    // select all products
    public List<Products> selectAllProducts(){
        return productsRepository.findAll();
    }

    // select product detail
    public ProductIdProjectionInterface selectProductDetl(Long idx){
        return productsRepository.selectProductDetl(idx);
    }

    // new product save
    public void saveNewProduct(ProductsAddDto addDto){
        // todo session data
        addDto.setCreatedDt(LocalDate.now());
        addDto.setCreateUsrId("admin");
        // save
        productsRepository.save(addDto.toEntity());
    }

}
