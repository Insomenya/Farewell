package com.example.farewell.repository;

import com.example.farewell.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findAllByOrdersIsNull();
}
