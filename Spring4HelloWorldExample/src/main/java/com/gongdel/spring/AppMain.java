package com.gongdel.spring;

import com.gongdel.spring.configuration.HelloWorldConfig;
import com.gongdel.spring.domain.HelloWorld;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class AppMain {

    public static void main(String[] args) {

        /*
        * AnnotationConfigApplicationContext는 Spring Application Context를 생성한다.
        * Spring Application Context는 @Configuration이 어노테이션 되어진 configuration class 파일의 입력을 받아들인다
        * 또한 Spring runtime 안에 있는  configuration class에 의해서 생성되어지는 모든 bean들을 등록한다.
        *
        * 한번 우리가 환경설정된 context를 얻는다면, 우리는 Spring application context로부터 구체적인 bean을 인출할 수 있는 getBean()를 사용할 수 있다.
        */
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(HelloWorldConfig.class);// XML 기반 Configuration을 사용한다면?
        HelloWorld bean = (HelloWorld) context.getBean("helloWorldBean");                             // new ClassPathXmlApplicationContext("~~~.xml");
        bean.sayHello("Spring 4");
        context.close();


        //원본 소스 : http://websystique.com/spring/spring-4-hello-world-example-annotation-tutorial-full-example/
    }
}
