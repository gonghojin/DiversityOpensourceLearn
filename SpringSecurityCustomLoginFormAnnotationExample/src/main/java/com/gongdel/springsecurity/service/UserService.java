package com.gongdel.springsecurity.service;

import com.gongdel.springsecurity.model.User;

import java.util.List;

public interface UserService {

    User findById(int id);

    User findBySso(String ss);

    void save(User user);

    void updateUser(User user);

    void deleteUserBySSO(String sso);

    List<User> findAllUsers();

    boolean isUserSSOUnique(Integer id, String sso);
}
