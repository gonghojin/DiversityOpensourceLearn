package com.gongdel.spring.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.gongdel.spring")
public class AppConfig {
}

/**
    이 클래스가 비어있는데 존재하는 단 하나의 이유는, beans auto_detection 기능을 하는 @Componentscan 때문이다.
    위의 configuration을 완전히 제거하고 component scan logic을 application context level(in Main)에 넣을 수도 있다.
    하지만 가득히 채워진 application에서는, Configuration class 안에 beans(예. messageSource, PropertySourcesPlaceHolderConfigurer)을 configuration 하는게
    다루기 편리할 것이다.
 */

/**
    XML Configuration

    <context:component-scan base-package="com.gongdel.spring" />


 **/