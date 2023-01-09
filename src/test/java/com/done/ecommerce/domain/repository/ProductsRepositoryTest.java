package com.done.ecommerce.domain.repository;

import com.done.ecommerce.domain.entity.Products;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
public class ProductsRepositoryTest {

    @Autowired
    private ProductsRepository productsRepository;

    @Test
    public void 상품저장_불러오기(){
        //given
        String name = "productName";
        String description = "productDescription";
        int price = 1000;
        LocalDate createDt = LocalDate.of(2022,01,07);
        String createUsrId = "admin";

        productsRepository.save(Products.builder()
                .name(name)
                .description(description)
                .price(price)
                .createdDt(createDt)
                .createUsrId(createUsrId)
                .build());

        //when
        List<Products> productsList = productsRepository.findAll();

        //then
        Products product = productsList.get(0);
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getDescription()).isEqualTo(description);
    }

    @Test
    public void 전체상품_불러오기(){
        //given
        for(int i = 0; i < 5; i++){
            productsRepository.save(Products.builder()
                    .name("name" + i)
                    .description("description" + i)
                    .price(1000 + i)
                    .createdDt(LocalDate.of(2022,01,07))
                    .createUsrId("admin" + i)
                    .build());
        }

        //when
        List<Products> rtnList = productsRepository.findAll();

        //then
        assertThat(rtnList.size()).isEqualTo(5);
    }
}
