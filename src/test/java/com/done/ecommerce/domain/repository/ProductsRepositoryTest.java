package com.done.ecommerce.domain.repository;

import com.done.ecommerce.domain.entity.Products;
import com.done.ecommerce.dto.products.ProductIdProjectionInterface;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    public void saveData(){
        for(int i = 1; i < 11; i++){
            productsRepository.save(Products.builder()
                    .name("name" + i)
                    .description("description" + i)
                    .price(10000 + i)
                    .created_dt(LocalDate.of(2022,01,07))
                    .create_usr_id("admin" + i)
                    .build());
        }
    }


    @Test
    public void 상품저장_불러오기(){
        // given
        String name = "productName";
        String description = "productDescription";
        int price = 1000;
        LocalDate createDt = LocalDate.of(2022,01,07);
        String createUsrId = "admin";
        int count = 11;

        productsRepository.save(Products.builder()
                .name(name)
                .description(description)
                .price(price)
                .created_dt(createDt)
                .create_usr_id(createUsrId)
                .build());

        // when
        List<Products> productsList = productsRepository.findAll();

        // then
        Products product = productsList.get(productsList.size()-1);
        assertThat(productsList.size()).isEqualTo(count);
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getDescription()).isEqualTo(description);
    }

    @Test
    public void 전체상품_불러오기(){
        // given
        int count = 10;

        // when
        List<Products> rtnList = productsRepository.findAll();

        // then
        assertThat(rtnList.size()).isEqualTo(count);
    }

    @Test
    public void 특정상품정보_불러오기(){
        // given
        Long id = 2l;

        // when
        ProductIdProjectionInterface rstDto = productsRepository.selectProductDetl(id);

        // then
        assertThat(rstDto.getId()).isEqualTo(id);
        assertThat(rstDto.getDescription()).isEqualTo("description2");
        assertThat(rstDto.getName()).isEqualTo("name2");
        assertThat(rstDto.getPrice()).isEqualTo(10002);
    }
}
