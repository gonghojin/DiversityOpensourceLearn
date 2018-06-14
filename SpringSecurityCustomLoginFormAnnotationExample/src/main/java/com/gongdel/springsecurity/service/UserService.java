package com.gongdel.springsecurity.service;

import com.gongdel.springsecurity.model.User;

public interface UserService {

    User findById(int id);

    User findBySso(String ss);

    void save(User user);
}
