package com.done.ecommerce.products.domain.repository;

import com.done.ecommerce.products.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    public Optional<Category> findCategoryById(Long id);

    public Optional<Category> findCategoryByCategoryName(String name);
}
