package com.gongdel.springsecurity.service;

import com.gongdel.springsecurity.model.UserDocument;

import java.util.List;

public interface UserDocumentService {

    UserDocument findById(int id);

    List<UserDocument> findAll();

    List<UserDocument> findAllByUserId(int id);

    void saveDocument(UserDocument document);

    void deleteById(int id);
}
