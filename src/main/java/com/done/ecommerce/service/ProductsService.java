package com.done.ecommerce.service;

import com.done.ecommerce.domain.entity.Products;
import com.done.ecommerce.domain.repository.ProductsRepository;
import com.done.ecommerce.dto.products.ProductIdProjectionInterface;
import com.done.ecommerce.dto.products.ProductsAddDto;
import com.done.ecommerce.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductsService {

    private final ProductsRepository productsRepository;
    private final HttpSession session;

    // select all products
    public List<Products> selectAllProducts(){
        return productsRepository.findAll();
    }

    // select product detail
    public ProductIdProjectionInterface selectProductDetl(Long id){
        return productsRepository.selectProductDetl(id);
    }

    // new product save
    public void saveNewProduct(ProductsAddDto addDto){
        addDto.setCreatedDt(LocalDate.now());
        addDto.setCreateUsrId(SessionUtil.getLoginUserId(session));
        // save
        productsRepository.save(addDto.toEntity());
    }

    public void deleteProduct(Long id){
        productsRepository.deleteById(id);
    }

}
