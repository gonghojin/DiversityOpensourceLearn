package com.gongdel.springsecurity.globalsecuritymethod.service;

import com.gongdel.springsecurity.dao.UserDao;
import com.gongdel.springsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserService_PostAuthorizeImpl implements UserService_PostAuthorize {

    static List<User> users = new ArrayList<User>();
    @Autowired
    UserDao dao;

    @Override
    public List<User> findAllUser() {
        return dao.findAllUsers();
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(int id) {

    }

    private static List<User> populateUser() {
        List<User> users = new ArrayList<User>();
    }
}
