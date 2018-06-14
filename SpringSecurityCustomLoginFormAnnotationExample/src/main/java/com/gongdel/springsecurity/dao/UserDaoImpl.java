package com.gongdel.springsecurity.dao;

import com.gongdel.springsecurity.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

    @Override
    public User findById(int id) {
        return getByKey(id);
    }

    @Override
    public User findBySSO(String sso) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("ssoId", sso));

        return (User) criteria.uniqueResult();
    }

    @Override
    public void save(User user) {
        persist(user);
    }
}
