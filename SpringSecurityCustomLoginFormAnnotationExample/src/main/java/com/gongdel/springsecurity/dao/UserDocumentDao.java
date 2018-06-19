package com.gongdel.springsecurity.dao;

import com.gongdel.springsecurity.model.UserDocument;

import java.util.List;

public interface UserDocumentDao {

    List<UserDocument> findAll();

    UserDocument findById(int id);

    void save(UserDocument document);

    void deleteById(int id);

    List findAllByUserId(int userid);
}