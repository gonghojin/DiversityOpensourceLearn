package com.gongdel.springsecurity.service;

import com.gongdel.springsecurity.model.User;
import com.gongdel.springsecurity.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
        User user = userService.findBySso(ssoId);
        System.out.println("User : " + user);
        if (user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getSsoId(), user.getPassword()
                , user.getState().equals("Active"), true, true, true, getGrantedAuthorites(user));
    }

    private List<GrantedAuthority> getGrantedAuthorites(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (UserProfile userProfile : user.getUserProfiles()) {
            System.out.println("UserProfile : " + userProfile);
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getType()));
        }
        System.out.println("authorities : " + authorities);

        return authorities;
    }
}
/**
 * 이 service는 Authentication Manager에게 authentication(인증) 세부사항을 제공하는 역할을 한다.
 * 이 service는 오직 하나의 메소드('loadUserByUsername')를 포함하고 있는 Spring의 'UserDetailsService' interface를 구현한다.
 * loadUserByUsername은 username[우리 예제에서는 ssoId]을 받고 'userdatils.User' 객체를 return한다.
 * 우리의 UserDao object를 사용하여 db의 data를 가져오는 UserService를 사용하여, 이 객체를 채운다.
 **/