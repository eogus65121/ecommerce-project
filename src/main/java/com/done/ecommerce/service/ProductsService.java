package com.done.ecommerce.service;

import com.done.ecommerce.domain.entity.Products;
import com.done.ecommerce.domain.repository.ProductsRepository;
import com.done.ecommerce.dto.products.ProductIdProjectionInterface;
import com.done.ecommerce.dto.products.ProductsAddDto;
import com.done.ecommerce.dto.products.ProductsDto;
import com.done.ecommerce.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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

    public void deleteProduct(Long id)  {
        productsRepository.deleteById(id);
    }

    public void productDataSet(Products products) {productsRepository.save(products);}

}
