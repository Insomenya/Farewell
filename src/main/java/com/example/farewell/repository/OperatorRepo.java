package com.example.farewell.repository;

import com.example.farewell.domain.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperatorRepo extends JpaRepository<Operator, Long> {
    Operator findByLogin(String login);
}
