package com.done.ecommerce.products.service;

import com.done.ecommerce.products.domain.entity.Category;
import com.done.ecommerce.products.dto.CategoryDto;
import com.done.ecommerce.products.dto.ProductIdProjectionInterface;
import com.done.ecommerce.products.dto.ProductsAddDto;
import com.done.ecommerce.products.dto.ProductsDto;

import java.util.List;

public interface ProductsService {

    public List<ProductsDto> selectAllProducts();

    public ProductIdProjectionInterface selectProductDetl(Long id);

    public void saveNewProduct(ProductsAddDto addDto);

    public void deleteProduct(Long id);

    public List<ProductIdProjectionInterface> findByCategory(int categoryId);

    public List<ProductIdProjectionInterface> selectProductByName(String name);

    public List<CategoryDto> selectAllCategory();

}
