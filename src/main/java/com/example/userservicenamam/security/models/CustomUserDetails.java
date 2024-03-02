package com.example.userservicenamam.security.models;

import com.example.userservicenamam.models.Role;
import com.example.userservicenamam.models.User;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonSerialize
public class CustomUserDetails implements UserDetails {//to store details of the user in db.

    private List<CustomGrantedAuthorities> authorities;
    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public CustomUserDetails() {}

    public CustomUserDetails(User user) {
//        this.user = user;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.enabled = true;
        this.credentialsNonExpired = true;
        this.password = user.getHashedPassword();
        this.username = user.getEmail();
        this.userId = user.getId();

        List<CustomGrantedAuthorities> grantedAuthorities = new ArrayList<>();

        for (Role role: user.getRoles()) {
            grantedAuthorities.add(new CustomGrantedAuthorities(role));
        }

        this.authorities = grantedAuthorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<CustomGrantedAuthority> grantedAuthorities = new ArrayList<>();
//
//        for (Role role: user.getRoles()) {
//            grantedAuthorities.add(new CustomGrantedAuthority(role));
//        }
//
//        return grantedAuthorities;
        return authorities;
    }

    @Override
    public String getPassword() {
//        return user.getHashedPassword();
        return password;
    }

    @Override
    public String getUsername() {
//        return user.getEmail();
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
