package com.done.ecommerce.products.domain.repository;

import com.done.ecommerce.products.domain.entity.Category;
import com.done.ecommerce.products.domain.entity.Products;
import com.done.ecommerce.products.dto.ProductIdProjectionInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

    // select product detail
    @Query(value = "select pd.id AS id, pd.name AS name, pd.description AS description, pd.price AS price, pd.createdDt AS createdDt" +
            ", pd.createUsrId AS createUsrId, pd.category AS category, pd.remark AS remark from Products AS pd where pd.id = :id")
    public ProductIdProjectionInterface selectProductDetl(@Param(value = "id") Long id);

    // 그룹 코드별 조회
    public List<ProductIdProjectionInterface> findByCategory(Category category);

    // 이름으로 상품 조회하기
    public List<ProductIdProjectionInterface> findByNameContains(String name);

}
