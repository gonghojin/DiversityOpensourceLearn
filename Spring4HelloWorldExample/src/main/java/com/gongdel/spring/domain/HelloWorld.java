package com.gongdel.spring.domain;


//Spring은 결합도를 낮추고 interface coding을 향진시킨다.
// spring bean 역할을 하는 POJO interface와 구현체를 생성
public interface HelloWorld {
    void sayHello(String name);
}
