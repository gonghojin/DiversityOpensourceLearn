package com.gongdel.spring.configuration;

import com.gongdel.spring.domain.HelloWorld;
import com.gongdel.spring.domain.HelloWorldImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

//Spring configuration class는 application이 필요할 bean 정의를 포함한다.
// @Configuration은 Spring Configuration class의 역할을 하는 클래스를 나타낸다
// 이 클래스는 Spring container에 의해서 관리되어지는 bean 정의를 발생시키는 @Bean annotated methods를 포함할 수 있다.
@Configuration
public class HelloWorldConfig {

    @Bean(name="helloWorldBean")
    @Description("이것은 간단한 HelloWordBean")// 목적을 표현하기 위해서 유용한 Annoation
    public HelloWorld helloWorld() {
        return new HelloWorldImpl();
    }

    /** *
     *
     *  위의 설정을 Xml config로 표현 시
         <bean id="helloWorldBean" class="com.websystique.spring.domain.HelloWorldImpl">
     *
     * */
}
