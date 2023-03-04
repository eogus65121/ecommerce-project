package com.done.ecommerce.domain.repository;

import com.done.ecommerce.products.domain.entity.Category;
import com.done.ecommerce.products.domain.entity.Products;
import com.done.ecommerce.products.domain.repository.CategoryRepository;
import com.done.ecommerce.products.dto.ProductIdProjectionInterface;
import com.done.ecommerce.products.domain.repository.ProductsRepository;
import com.done.ecommerce.products.exception.CategoryNotFoundException;
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
    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void saveData(){
        for(int i = 1; i < 11; i++){
            // 카테고리 데이터 셋팅
            categoryRepository.save(Category.builder().categoryName("categoryName" + i).build());

            Long n = new Long(i);

            // 카테고리 엔티티 조회
            Category category = categoryRepository.findCategoryById(n)
                            .orElseThrow(() -> new CategoryNotFoundException(n.toString()));

            // 상품 저장
            productsRepository.save(Products.builder()
                    .name("name" + i)
                    .description("description" + i)
                    .price(10000 + i)
                    .category(category)
                    .createdDt(LocalDate.of(2022,01,07))
                    .createUsrId("admin" + i)
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
                .createdDt(createDt)
                .createUsrId(createUsrId)
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

    @Test
    public void 상품_삭제(){
        //given
        Long id = 3l;
        int currentSize = productsRepository.findAll().size();

        //when
        productsRepository.deleteById(id);
        int sizeAfterDelete = productsRepository.findAll().size();

        //then
        assertThat(sizeAfterDelete).isEqualTo(currentSize-1);
    }

    @Test
    public void 그룹코드별_조회(){
        //given
        Category paramCategory = categoryRepository.findById(2l).orElseThrow();

        //when
        List<ProductIdProjectionInterface> list = productsRepository.findByCategory(paramCategory);

        //then
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    public void 상품_이름조회(){
        //given
        String name = "name";
        String name2 = "name2";

        //when
        List<ProductIdProjectionInterface> list = productsRepository.findByNameContains(name);
        List<ProductIdProjectionInterface> list2 = productsRepository.findByNameContains(name2);

        //then
        assertThat(list.size()).isEqualTo(10);
        assertThat(list2.size()).isEqualTo(1);
    }
}
