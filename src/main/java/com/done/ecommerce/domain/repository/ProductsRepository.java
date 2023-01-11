package com.done.ecommerce.domain.repository;

import com.done.ecommerce.domain.entity.Products;
import com.done.ecommerce.dto.products.ProductIdProjectionInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

    // select product detail
    @Query(value = "select pd.id AS id, pd.name AS name, pd.description AS description, pd.price AS price, pd.created_dt AS createdDt" +
            ", pd.create_usr_id AS createUsrId, pd.group_id AS groupId, pd.remark AS remark from Products AS pd where pd.id = :id")
    ProductIdProjectionInterface selectProductDetl(@Param(value = "id") Long id);

}
