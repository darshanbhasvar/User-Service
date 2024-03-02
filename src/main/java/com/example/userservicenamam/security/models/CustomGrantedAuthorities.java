package com.example.userservicenamam.security.models;

import com.example.userservicenamam.models.Role;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.core.GrantedAuthority;

@JsonSerialize

public class CustomGrantedAuthorities implements GrantedAuthority {

    public CustomGrantedAuthorities() {
    }

    private Role role;

    public CustomGrantedAuthorities(Role role) {
        this.role = role;
    }
    @Override
    public String getAuthority() {
        return role.getName();
    }
}
