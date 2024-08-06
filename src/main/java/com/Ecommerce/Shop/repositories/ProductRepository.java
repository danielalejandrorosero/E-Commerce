package com.Ecommerce.Shop.repositories;

import com.Ecommerce.Shop.entities.Category;


import org.springframework.data.jpa.repository.JpaRepository;
import com.Ecommerce.Shop.entities.Product;


import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductByTitle(String title);
    List<Product> findProductsByCategory(Category category);
    Product findProductById(Integer id);
}
