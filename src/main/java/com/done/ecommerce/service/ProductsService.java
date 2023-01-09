package com.done.ecommerce.service;

가import com.done.ecommerce.domain.entity.Products;
import com.done.ecommerce.domain.repository.ProductsRepository;
import com.done.ecommerce.dto.products.ProductsAddDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    public void saveNewProduct(ProductsAddDto addDto){
        // todo session data
        addDto.setCreatedDt(LocalDate.now());
        addDto.setCreateUsrId("admin");
        // save
        productsRepository.save(addDto.toEntity());
    }

    public List<Products> selectAllProducts(){
        return productsRepository.findAll();
    }
}
