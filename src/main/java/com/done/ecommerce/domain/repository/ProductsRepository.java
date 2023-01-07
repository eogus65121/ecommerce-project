package com.done.ecommerce.domain.repository;

import com.done.ecommerce.domain.entity.Products;
import com.done.ecommerce.dto.products.ProductsAddDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    public void addProduct(ProductsAddDto productsAddDto) throws Exception;
}
