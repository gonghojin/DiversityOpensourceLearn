package com.gongdel.springsecurity.dao;

import com.gongdel.springsecurity.model.UserProfile;

import java.util.List;

public interface UserProfileDao {

    List<UserProfile> findAll();

    UserProfile findById(int id);

    UserProfile findByType(String type);
}
