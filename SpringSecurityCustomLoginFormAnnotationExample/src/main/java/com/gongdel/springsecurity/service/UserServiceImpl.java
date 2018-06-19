package com.gongdel.springsecurity.service;

import com.gongdel.springsecurity.dao.UserDao;
import com.gongdel.springsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findById(int id) {
        return dao.findById(id);
    }

    @Override
    public User findBySso(String sso) {
        return dao.findBySSO(sso);
    }

    /**
     * database에  새로운 password를 저장하기 전에 password encoding 후 저장
     **/
    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
    }

    /**
     * method가 Transaction과 함께 실행되기 떄문에, hibernate update를 명확하게 호출할 필요가 없다.
     * 단지 transaction 안에서 db로부터 entity를 인출하고 그 값을 적절한 value로 수정하면, trascation이 끝났을 때 db값이 updated된다.
     */
    @Override
    public void updateUser(User user) {
        User entity = dao.findById(user.getId());
        if (entity != null) {
            entity.setSsoId(user.getSsoId());
            entity.setPassword(user.getPassword());
            entity.setFirstName(user.getFirstName());
            entity.setLastName(user.getLastName());
            entity.setEmail(user.getEmail());
            entity.setUserProfiles(user.getUserProfiles());
        }
    }

    @Override
    public void deleteUserBySSO(String sso) {
        dao.deleteBySSO(sso);
    }

    @Override
    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }

    @Override
    public boolean isUserSSOUnique(Integer id, String sso) {
        User user = findBySso(sso);
        return (user == null || ((id != null) && (user.getId() == id)));
    }
}
