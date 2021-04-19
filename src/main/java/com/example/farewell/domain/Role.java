package com.example.farewell.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    CUSTOMER, OPERATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}