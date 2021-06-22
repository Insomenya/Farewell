package com.example.farewell.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Collection;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    @Positive(message = "Price isn't set.")
    @Column(nullable = false)
    private double price;
    @NotBlank(message = "Empty product name.")
    @Column(nullable = false)
    private String name;
    private Short state;
    @Length(max = 2048, message = "Description is too long.")
    @Column(length = 2048)
    private String description;
    private String image;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "category_id",
            foreignKey = @ForeignKey(name = "product_fk_category")
    )
    private Category category;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "added_by",
            foreignKey = @ForeignKey(name = "product_fk_operator")
    )
    private Operator addedBy;
    @ManyToMany(
            mappedBy = "products",
            fetch = FetchType.EAGER
    )
    private List<Order> orders;

    public Product() {
    }

    public Product(String name, double price, Short state, String description, Category category, Operator addedBy) {
        this.name = name;
        this.price = price;
        this.state = state;
        this.description = description;
        this.category = category;
        this.addedBy = addedBy;
    }

    public Long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public Short getState() {
        return state;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public Category getCategoryId() {
        return category;
    }

    public Operator getAddedBy() {
        return addedBy;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategoryId(Category categoryId) {
        this.category = categoryId;
    }

    public void setAddedBy(Operator addedBy) {
        this.addedBy = addedBy;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
