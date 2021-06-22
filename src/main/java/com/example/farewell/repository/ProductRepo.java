package com.example.farewell.repository;

import com.example.farewell.domain.Category;
import com.example.farewell.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Page<Product> findAllByOrdersIsNull(Pageable page);

    Page<Product> findAllByOrdersIsNullAndCategoryIs(Category category, Pageable pageable);
}
