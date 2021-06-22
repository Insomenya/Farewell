package com.example.farewell.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    @NotBlank(message = "No phone number.")
    @Column(
            name = "phone_num",
            nullable = false,
            length = 15,
            unique = true
    )
    private String phoneNum;
    @Column(columnDefinition = "float8 default 0")
    private double personalDiscount;
    @NotBlank(message = "Password left blank.")
    @Column(nullable = false)
    private String password;
    @OneToMany(
            mappedBy = "customer",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    private Collection<Order> orders;
    /*@Transient
    private Order currentOrder;*/

    public Customer() {
    }

    public Customer(String fullName, String phoneNum, Float personalDiscount, String password, Collection<Order> orders) {
        this.fullName = fullName;
        this.phoneNum = phoneNum;
        this.personalDiscount = personalDiscount;
        this.password = password;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public double getPersonalDiscount() {
        return personalDiscount;
    }

    public String getPassword() {
        return password;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

    public void setPersonalDiscount(double personalDiscount) {
        this.personalDiscount = personalDiscount;
    }

    public Role getRole() { return Role.ROLE_CUSTOMER; }

    /*public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }*/
}
