package com.example.farewell.repository;

import com.example.farewell.domain.Operator;
import com.example.farewell.pojo.OperatorOrderCountStatistic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OperatorOrderCountRepository extends CrudRepository<Operator, Long> {
    @Query("SELECT " +
            "new com.example.farewell.pojo.OperatorOrderCountStatistic(o.processedBy, COUNT(o) ) " +
            "FROM " +
            "order_table o " +
            "WHERE o.status = 2 " +
            "GROUP BY " +
            "o.processedBy"
    )
    List<OperatorOrderCountStatistic> findOrderCountByOperator();
}
