package com.gongdel.springsecurity.dao;

import com.gongdel.springsecurity.model.User;

import java.util.List;

public interface UserDao {
    User findById(int id);

    User findBySSO(String sso);

    void save(User user);

    List<User> findAllUsers();
}
