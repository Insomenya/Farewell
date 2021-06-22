package com.example.farewell.repository;

import com.example.farewell.domain.Category;
import com.example.farewell.domain.Operator;
import com.example.farewell.pojo.CategoryOrderCountStatistic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryOrderCountRepository extends CrudRepository<Category, Long> {

    @Query("SELECT new com.example.farewell.pojo.CategoryOrderCountStatistic(p.category, COUNT(p.category) ) FROM order_table o join o.products p where o.status = 2 GROUP BY p.category")
    List<CategoryOrderCountStatistic> findOrderCountByCategory();
}
