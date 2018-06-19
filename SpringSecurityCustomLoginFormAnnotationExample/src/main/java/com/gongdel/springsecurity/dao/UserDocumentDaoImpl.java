package com.gongdel.springsecurity.dao;

import com.gongdel.springsecurity.model.UserDocument;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDocumentDao")
public class UserDocumentDaoImpl extends AbstractDao<Integer, UserDocument> implements UserDocumentDao {

    @Override
    public List<UserDocument> findAll() {
        Criteria criteria = createEntityCriteria();
        return (List<UserDocument>) criteria.list();
    }

    @Override
    public UserDocument findById(int id) {
        return getByKey(id);
    }

    @Override
    public void save(UserDocument document) {
        persist(document);
    }

    @Override
    public void deleteById(int id) {
        UserDocument criteria = getByKey(id);

    }

    @Override
    public List<UserDocument> findAllByUserId(int userid) {
        Criteria criteria = createEntityCriteria();
        Criteria userCriteria = criteria.createCriteria("user");
        userCriteria.add(Restrictions.eq("id", userid));

        return (List<UserDocument>) criteria.list();
    }
}
