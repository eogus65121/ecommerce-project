package com.done.ecommerce.products.service;

import com.done.ecommerce.products.domain.entity.Products;
import com.done.ecommerce.products.domain.repository.ProductsRepository;
import com.done.ecommerce.products.dto.ProductIdProjectionInterface;
import com.done.ecommerce.products.dto.ProductsAddDto;
import com.done.ecommerce.products.dto.ProductsDto;
import com.done.ecommerce.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductsService {

    private final ProductsRepository productsRepository;
    private final HttpSession session;
    private final ModelMapper modelMapper;

    // select all products
    @Cacheable(value = "PRODUCT_SEARCH_ALL")
    public List<ProductsDto> selectAllProducts() {
        List<Products> list = productsRepository.findAll();

        // Entity 결과값에서 DTO로 변경
        List<ProductsDto> rtnList = list.stream()
                .map(post -> modelMapper.map(post, ProductsDto.class))
                .collect(Collectors.toList());

        return rtnList;
    }

    public List<Products> test() {
        return productsRepository.findAll();
    }

    // select product detail
    public ProductIdProjectionInterface selectProductDetl(Long id) {
        return productsRepository.selectProductDetl(id);
    }

    // new product save
    public void saveNewProduct(ProductsAddDto addDto) {
        addDto.setCreatedDt(LocalDate.now());
        addDto.setCreateUsrId(SessionUtil.getLoginUserId(session));
        // save
        productsRepository.save(addDto.toEntity());
    }

    @Transactional
    public void deleteProduct(Long id)  {
        productsRepository.deleteById(id);
    }

    // select by group id
    public List<ProductIdProjectionInterface> findByCategory(int category){
        List<ProductIdProjectionInterface> list = productsRepository.findByCategory(category);
        return list;
    }

    public void productDataSet(Products products) {productsRepository.save(products);}

    public List<ProductIdProjectionInterface> selectProductByName(String name){
        return productsRepository.findByNameContains(name);
    }

}
