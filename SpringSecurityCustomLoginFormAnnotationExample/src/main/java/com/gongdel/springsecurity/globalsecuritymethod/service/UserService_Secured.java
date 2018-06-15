package com.gongdel.springsecurity.globalsecuritymethod.service;

import com.gongdel.springsecurity.model.User;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface UserService_Secured {

    List<User> findAllUsers();

    @Secured("ROLE_ADMIN")
    void updateUser(User user);

    @Secured({"ROLE_DBA", "ROLE_ADMIN"})
    void deleteUser();
}
/**
    @Secured annotation은 비즈니스 method를 위한 security configuration 속성을 정의하기 위해 사용된다.
    method 위에 @Secured를 사용하여 security 요구사항[roles/permission 등]을 명시할 수 있으며,
    이 roles, permission을 가진 user만이 해당 method를 invoke(작동시키다)할 수 있다.
    만약 필요한 roles나 permission을 소유하지 못한 누군가가 method를 작동시키려 한다며, AccessDenied(접근부정) exception을 발생시킨다.

    @Secured는 스프링의 이전 버전에서 제공된다. 따라서 Spring EL 표현을 지원하지 못 한다.
 **/

/**
    위의 예에서, updateUser method는 Admin 권한을 가진 누군가가 작동시키는 반면, deleteUser는 DBA 'OR' ADMIN 권한을 가진 누군가가 작동시킬 수 있다.
    만약  'And' 조건을 명시하기 윈한다면(예 ADMIN이면서 DBA), @Secured annotation은 즉시 가능하지 않다.
    EL표현을 지원하는 새로운 @PreAuthorized/@PostAuthorized annotation을 사용해야 한다.


 **/