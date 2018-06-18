package com.gongdel.springsecurity.dao;

import com.gongdel.springsecurity.model.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

    @Override
    public User findById(int id) {
        User user = getByKey(id);
        if (user != null) {
            Hibernate.initialize(user.getUserProfiles());
        }

        return user;
    }

    @Override
    public User findBySSO(String sso) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("ssoId", sso));
        User user = (User) criteria.uniqueResult();
        if (user != null) {
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
    }

    @Override
    public void save(User user) {
        persist(user);
    }

    @Override
    public void deleteBySSO(String sso) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("ssoId", sso));
        User user = (User) criteria.uniqueResult();
        delete(user);

    }

    @Override
    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); // Profile과 m : n 관계이기 떄문에 join 시 중복 user 값 생김, 따라서 중복을 피하기 위해
        List<User> users = (List<User>) criteria.list();
        /**
         * list page에 userProfiles를 보여주지 않기 때문에, userProfiles의 인출이 필요하지 않음
         * 즉 userProfiles를 인출하기 위한 아래의 line이 필요없음.
         * 만약 인출을 원한다면,
         *  for (User user : users) {
         *      Hibernate.initialize(user.getUserProfiles());
         *  }
         */
        return users;
    }
}
