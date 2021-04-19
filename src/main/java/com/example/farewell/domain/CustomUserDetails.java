package com.example.farewell.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    @NotBlank(message = "Username is empty.")
    private String username;
    @NotBlank(message = "Password is empty.")
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Customer customer;
    private Operator operator;

    public CustomUserDetails() {
        super();
    }

    public CustomUserDetails(String username, String password, Role role, Operator operator) {
        this.username = username;
        this.password = password;
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(role);
        this.authorities = grantedAuthorities;
        this.operator = operator;
    }

    public CustomUserDetails(String username, String password, Role role, Customer customer) {
        this.username = username;
        this.password = password;
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(role);
        this.authorities = grantedAuthorities;
        this.customer = customer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public boolean isOperator(){
        return getAuthorities().contains(Role.OPERATOR);
    }

    public boolean isCustomer(){
        return getAuthorities().contains(Role.CUSTOMER);
    }
}
