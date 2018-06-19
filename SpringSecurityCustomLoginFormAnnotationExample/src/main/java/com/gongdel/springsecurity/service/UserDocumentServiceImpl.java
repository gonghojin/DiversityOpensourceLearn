package com.gongdel.springsecurity.service;

import com.gongdel.springsecurity.dao.UserDocumentDao;
import com.gongdel.springsecurity.model.UserDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userDocumentService")
@Transactional
public class UserDocumentServiceImpl implements UserDocumentService {

    @Autowired
    UserDocumentDao userDocumentDao;

    @Override
    public UserDocument findById(int id) {
        return userDocumentDao.findById(id);
    }

    @Override
    public List<UserDocument> findAll() {
        return userDocumentDao.findAll();
    }

    @Override
    public List<UserDocument> findAllById(int id) {
        return userDocumentDao.findAllByUserId(id);
    }

    @Override
    public void saveDocument(UserDocument document) {
        userDocumentDao.save(document);
    }

    @Override
    public void deleteById(int id) {
        userDocumentDao.deleteById(id);
    }
}
