package com.Ecommerce.Shop.repositories;


import com.Ecommerce.Shop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findCategoryByTitle(String title);
}