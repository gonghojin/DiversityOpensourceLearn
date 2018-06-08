package com.gongdel.springsecurity.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

// 아래의 initializer class(초기화 클래스)는 application war에 springSecurityFilter(SecurityConfiguration.class에 설정한)를 등록한다.
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
}

/**
    <XML configuration>

         <filter>
             <filter-name>springSecurityFilterChain</filter-name>
             <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
         </filter>

         <filter-mapping>
             <filter-name>springSecurityFilterChain</filter-name>
             <url-pattern>/*</url-pattern>
         </filter-mapping>

 **/