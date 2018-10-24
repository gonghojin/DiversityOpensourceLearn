package com.sample.service.security;

import com.sample.domain.security.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

public interface UserService extends UserDetailsService {

    Collection<GrantedAuthority> getAuthorities(String username);
    public User readUser(String username);
}
