package com.gongdel.spring.domain;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("application")
public class Application {

    @Resource(name = "applicationUser")
    private ApplicationUser user;

    @Override
    public String toString() {
        return "Application{" +
                "user=" + user +
                '}';
    }
}

/**
 *   표준 @Resource annotation은 application이 필요할 resource를 표시한다.
 *   @Autowired와 비슷한 점은, 속성이 제공되지 않았을 때 둘다 type에 의한 bean을 inject하는 것이다.
 *   ★그러나 이름 속성과 함께할 경우, @Reousrce는 그 이름에 의한 inject를 가능하게 해주지만 @Autowired는 그러지 못한다.★
 *
 *   위 코드에서 Application의 user property는 @Resource(name = "applicationUser")가  annotaed 되어져 있다.
 *   이 경우, applicationContext 안에서 name 'applicationUser'를 가진 bean이 찾아지고 injected 되어진다. 
 *
 *
 *
 * **/