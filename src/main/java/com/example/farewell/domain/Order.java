package com.example.farewell.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity(name = "order_table")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private short status;
    private double total;
    @Column(name = "order_date")
    private LocalDate date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "customer_id",
            foreignKey = @ForeignKey(name = "order_fk_customer")
    )
    private Customer customer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "processed_by",
            foreignKey = @ForeignKey(name = "order_fk_operator")
    )
    private Operator processedBy;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "product_list",
            joinColumns = @JoinColumn(name = "order_id" , foreignKey = @ForeignKey(name = "product_list_fk_order")),
            inverseJoinColumns = @JoinColumn(name = "product_id" , foreignKey = @ForeignKey(name = "product_list_fk_product"))
    )
    private List<Product> products;

    public Order() {
    }

    public Order(short status, LocalDate date, Customer customer) {
        this.status = status;
        this.date = date;
        this.customer = customer;
        this.processedBy = processedBy;
    }

    public Long getId() {
        return id;
    }

    public short getStatus() {
        return status;
    }

    public LocalDate getDate() {
        return date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Operator getProcessedBy() {
        return processedBy;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setProcessedBy(Operator processedBy) {
        this.processedBy = processedBy;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }
}
