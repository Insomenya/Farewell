package com.example.farewell.repository;

import com.example.farewell.domain.Customer;
import com.example.farewell.domain.Operator;
import com.example.farewell.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    Page<Order> findAllByCustomerOrderByDate(Customer customer, Pageable page);

    Page<Order> findAllByProcessedByOrderByDate(Operator processedBy, Pageable page);

    List<Order> findAllByStatusEqualsOrderByDate(short status);
}
