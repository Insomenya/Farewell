package com.example.farewell.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Entity(name = "store_operator")
public class Operator {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    @NotBlank(message = "Login left blank.")
    @Column(nullable = false)
    private String login;
    @NotBlank(message = "Password left blank.")
    @Column(nullable = false)
    private String password;
    @OneToMany(
            mappedBy = "addedBy",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    private Collection<Product> products;
    @OneToMany(
            mappedBy = "processedBy",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    private Collection<Order> orders;

    public Operator() {
    }

    public Operator(String fullName, String login, String password) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return Role.OPERATOR;
    }
}
