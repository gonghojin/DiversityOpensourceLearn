package com.gongDel.spring.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration // 이 클래스는 @Bean로 annotated 되어지는 method들을 포함한다.
                // @Bean annotated methods은 Spring container에 의해서 관리되는 Bean을 발생시킨다.
@ComponentScan(basePackages = "com.gongDel.spring")// 그러나 왜 @Bean method가 비어있을까?
public class AppConfig {                           // 바로 @Component annotation 덕분에 자동적으로 팀색되어지기 때문이다.
}

/* XML configuration으로 표현 시
 *
 *   <context:component-scan base-package="com.gongDel.spring" />
 * */

/**
 *   @ComponentScan에 basePackages 속성은 Spring의 특별한 annoation들이 할당된 클래스를 찾기 위한 package name[s]을 가진다.
 *   아래의 annotation이 자동 탐색이 가능하도록 만들어주는 Spring annoation이다.
 *
 *   @Repository - persistence layer(영속성 계층)의 DAO Component를 표시
 *   @Service - business layer(비즈니스 계층)의 Service Component를 표시
 *   @Controller - Presentation layer(표현 계층)의 Controller component를 표시
 *   @Configuration - 환경설정 Component
 *   @Component - 일반적인 목적의 annotation , 위의 annotations의 대체로서 사용되어 질 수 있다.
 *
 *  위의 모든 annotations이 내부적으로 @Component를 할당하고 있음.  따라서 @Component를 어디서든 사용할 수 있음
    그러나 디자인 & 목적을 분명하게 하기 위해서, 구분해서 사용하는 게 좋음
 * */