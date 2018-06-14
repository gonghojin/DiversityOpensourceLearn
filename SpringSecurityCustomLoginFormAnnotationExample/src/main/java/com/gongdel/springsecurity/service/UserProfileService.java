package com.gongdel.springsecurity.service;

import com.gongdel.springsecurity.model.UserProfile;

import java.util.List;

public interface UserProfileService {

    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);
}
