package com.sample.service.security;

import com.sample.dao.security.mapper.UserMapper;
import com.sample.domain.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Collection<GrantedAuthority> getAuthorities(String username) {
        // db에 저장된 권한
        List<String> mapperAutorities = userMapper.readAuthority(username);

        // Security가 해당 권한을 인식하도록 설정
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String authority : mapperAutorities) {
            authorities.add(new SimpleGrantedAuthority(authority));
        }

        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.readUser(username);
        user.setAuthorities(getAuthorities(username));
        return user;

    }

    @Override
    public User readUser(String username) {
        User user = userMapper.readUser(username);

        return user;
    }
}
