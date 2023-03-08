package com.done.ecommerce.domain.repository;


import com.done.ecommerce.products.domain.entity.Category;
import com.done.ecommerce.products.domain.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void saveCategory(){
        for(int i = 1; i < 10; i++){
            // 카테고리 데이터 셋팅
            categoryRepository.save(Category.builder()
                    .categoryName("categoryName" + i).build());
        }
    }

    @Test
    void id_카테고리_조회_성공(){
        //given
        long requestId = 3l;

        //when
        Optional<Category> selectCategory = categoryRepository.findCategoryById(requestId);
        String name = selectCategory.get().getCategoryName();
        Long id = selectCategory.get().getId();

        //then
        assertThat(name).isEqualTo("categoryName3");
        assertThat(id).isEqualTo(requestId);

    }

    @Test
    void id_카테고리_조회_실패(){
        //given
        long requestId = 15l;

        //when
        Optional<Category> selectCategory = categoryRepository.findCategoryById(requestId);

        Assertions.assertThrows(Exception.class, () -> {
            selectCategory.orElseThrow(() -> new Exception());
        });
    }

    @Test
    void name_카테고리_조회_성공(){
        //given
        String requestName = "categoryName4";

        //when
        Optional<Category> selectCategory = categoryRepository.findCategoryByCategoryName(requestName);
        Long id = selectCategory.get().getId();
        String name = selectCategory.get().getCategoryName();

        //then
        assertThat(id).isEqualTo(4l);
        assertThat(name).isEqualTo(requestName);

    }
    @Test
    void name_카테고리_조회_실패(){
        //given
        String requestName = "categoryName20";

        //when
        Optional<Category> selectCategory = categoryRepository.findCategoryByCategoryName(requestName);

        //then
        Assertions.assertThrows(Exception.class, () -> {
            selectCategory.orElseThrow(() -> new Exception());
        });

    }
}
