package com.gongdel.spring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMain {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");
    }

    /**
     *  주목할 점은 여기에서 어떠한 scheduling class or method를 호출하지 않았다.
     *  app-config.xml 안에 context를 설정했기 때문에, context는 load 되어지고 scheduling을 설정할 것이다.
     */
}
