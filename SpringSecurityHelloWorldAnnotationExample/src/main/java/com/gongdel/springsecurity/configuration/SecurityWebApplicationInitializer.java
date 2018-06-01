package com.gongdel.springsecurity.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

// 명시된 initializer class는 우리의 application war에 'SpringSecurityFilter'[SecurityConfiguration에서 생성된]를 등록한다.
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
}

/**
     XML configuration

        <filter>
            <filter-name>springSecurityFilterChain</filter-name>
            <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
        </filter>

        <filter-mapping>
            <filter-name>springSecurityFilterChain</filter-name>
            <url-pattern>/*</url-pattern>
        </filter-mapping>
 **/