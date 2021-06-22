package com.example.farewell.pojo;

import com.example.farewell.domain.Category;

public class CategoryOrderCountStatistic {
    private Category category;
    private Long orderCount;

    public CategoryOrderCountStatistic(Category category, Long orderCount) {
        this.category = category;
        this.orderCount = orderCount;
    }

    public Category getCategory() {
        return category;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }
}
