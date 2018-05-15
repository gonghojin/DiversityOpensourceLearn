package com.gongDel.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = "com.gongDel.spring")
@PropertySource(value = { "classpath:application.properties" })
public class AppConfig {
    /**
        PropertySourcePlaceHolderConfigurer Bean 오직 @Value("{}") annoations을 위해서 필요하다.
        따라서 만약 injecting properties를 위한 @Value annotations을 사용하지 않는다면 이 bean을 제거
     **/
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}

    /**
        @PropertySource(value = { "classpath:application.properties" }) annotation은 properties을 Environment에 만들다.(value attribute에 의해서 참조됨)
        Environment interface는 각각의 property를 읽기 위한 getter methods를 application에 제공한다.
     **/

    /*
        XML 환경설정

        <context:component-scan base-package="com.gongDel.spring" />

        <bean class="org.springframework.context.support.PropertySourcesPlaceholderConfigurer">
            <property name="ignoreUnresolvablePlaceholders" value="true" />
            <property name="locations">
                <list>
                    <value>classpath:application.properties</value>
                </list>
            </property>
        </bean>
     */