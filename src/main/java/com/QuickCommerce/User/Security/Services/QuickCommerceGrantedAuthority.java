package com.QuickCommerce.User.Security.Services;

import com.QuickCommerce.User.Models.Role;
import org.springframework.security.core.GrantedAuthority;

public class QuickCommerceGrantedAuthority implements GrantedAuthority {
    private String authority;

    public QuickCommerceGrantedAuthority(Role role) {
        this.authority = role.getName();
    }
    @Override
    public String getAuthority() {
        return authority;
    }
}
