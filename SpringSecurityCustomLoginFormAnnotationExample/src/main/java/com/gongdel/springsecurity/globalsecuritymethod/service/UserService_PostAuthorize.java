package com.gongdel.springsecurity.globalsecuritymethod.service;

import com.gongdel.springsecurity.model.User;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface UserService_PostAuthorize {

    List<User> findAllUser();

    @PostAuthorize("returnObject.type == authentication.name")
    User findById(int id);

    @PreAuthorize("hasRole('ADMIN')")
    void updateUser(User user);

    @PreAuthorize("hasRole('ADMIN') AND hasRole('DBA')")
    void deleteUser(int id);
}
/**
    @PreAuthorized는 Spring Expression 언어를 사용할 수 있기 때문에, El을 사용하여 조건을 쉽게 표현할 수 있다.
    Method deletedUser는 ADMIN과 DBA 권한을 둘다 가진 user가 작동시킬 수 있도록 설정됐다.

    @PostAuthorized annotation을 findById() method에 추가했기 떄문에, method(User object)의 return 값을 'returnObject'를 통해 Spring Expression 언어에 접근한다.
    그리고 return된 user 객체의 개개의 속성은 security rule에 적용하기 위해 사용될 수 있다.
    이 예제에서는 로그인 user가 오직 자기 자신의 User type 객체만 가져온다.
 **/