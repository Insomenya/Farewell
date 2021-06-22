package com.example.farewell.pojo;

import com.example.farewell.domain.Operator;

public class OperatorOrderCountStatistic {
    private Operator operator;
    private Long orderCount;

    public OperatorOrderCountStatistic(Operator operator, Long orderCount) {
        this.operator = operator;
        this.orderCount = orderCount;
    }

    public Operator getOperator() {
        return operator;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }
}
