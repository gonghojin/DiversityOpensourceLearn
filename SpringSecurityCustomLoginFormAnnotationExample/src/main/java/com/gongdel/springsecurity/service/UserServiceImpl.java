package com.gongdel.springsecurity.service;

import com.gongdel.springsecurity.dao.UserDao;
import com.gongdel.springsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Override
    public User findById(int id) {
        return dao.findById(id);
    }

    @Override
    public User findBySso(String sso) {
        return dao.findBySSO(sso);
    }
}
