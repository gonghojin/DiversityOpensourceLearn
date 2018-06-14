package com.gongdel.springsecurity.service;

import com.gongdel.springsecurity.dao.UserDao;
import com.gongdel.springsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findById(int id) {
        return dao.findById(id);
    }

    @Override
    public User findBySso(String sso) {
        return dao.findBySSO(sso);
    }

    /**
     * database에  새로운 password를 저장하기 전에 password encoding 후 저장
     **/
    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
    }
}
