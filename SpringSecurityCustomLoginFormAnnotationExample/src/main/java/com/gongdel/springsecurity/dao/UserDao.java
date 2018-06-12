package com.gongdel.springsecurity.dao;

import com.gongdel.springsecurity.model.User;

public interface UserDao {
    User findById(int id);

    User findBySSO(String sso);

}
