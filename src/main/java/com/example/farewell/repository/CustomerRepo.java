package com.example.farewell.repository;

import com.example.farewell.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByPhoneNum(String phoneNum);
}
