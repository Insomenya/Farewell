package com.example.farewell.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_CUSTOMER, ROLE_OPERATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}