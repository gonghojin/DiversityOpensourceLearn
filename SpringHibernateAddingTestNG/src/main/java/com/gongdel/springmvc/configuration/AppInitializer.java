package com.gongdel.springmvc.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { AppConfig.class };
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
       위의 내용은 front-controller 'DispatherServler'을 사용하기 때문에 web.xml의 내용과 유사하다.
        mapping(xml에서 url-pattern)을 할당하고, Spring configuration file(spring-servlet.xml)에 경로를 대신 제공한다.
        여기에 우리는 Configuration class를 등록한다.
 **/

/** 업데이트 : 위의 설정은 'AbstractAnnotationConfigDispatcherServletInitializer' 기반 class를 사용함으로써, 더 간결하게 쓸 수 있다. ( 더 선호되어지는 방법)
    ex)

        public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

            @Override
            protected Class<?>[] getRootConfigClasses() {
                return new Class[] { AppConfig.class };
            }

            @Ovverride
            protected Class<?>[] getServletConfigClasses() {
                return null;
            }

            @Override
            protected String[] getServletMappings() {
                return new String[] { "/" };
            }
        }

 **/