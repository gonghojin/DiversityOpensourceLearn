package com.gongdel.springsecurity.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { HelloWorldConfiguration.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}

/**
 *  모든 'WelApplicationInitializer' 구현체를 위한 기본 클래스인 AbstractAnnotationConfigDispatcherServletInitializer를 상속한다.
 *  Sevlet 3.0 환경을 위해 ,'WeapplicationInitializer'는 SevletContext를 프로그래밍적(programatically)으로 환경설정한다.
 *  이는 web.xml을 사용하지 않고 app을 Sevlet 3.0 container에 deploy(배치)하는 것을 의미한다
 */
