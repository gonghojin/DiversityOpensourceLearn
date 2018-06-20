package com.gongdel.springsecurity.configuration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /** 파일업로드를 위한 변수**/
    private static final String LOCATION = "C:/zzz/";
    private static final long MAX_FILE_SIZE = 1024 * 1024 * 25; // 25MB
    private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 30; // 30MB
    private static final int FILE_SIZE_THRESHOLD = 0;

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{HelloWorldConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(getMultipartConfigElement());
    }

   /* @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        return new Filter[] {characterEncodingFilter};
    }*/

    private MultipartConfigElement getMultipartConfigElement() {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);

        return multipartConfigElement;
    }
}

/**
    Spring은 file을 upload하기 위한 몇 가지 수단을 제공한다.
    우리는 가능한 방법 중 하나인, SpringMvc DispatcherSerlvet의 'javax.servlet.MultipartConfigElement를 사용하여 Programmatic한 등록을 한다.
    이 등록은 명시된 속성들[maximum file size, request size, location, threshold 같은]을 설정할 수 있는 기회를 제공한다.
    그후에 file은 upload가 작동하는 동안 일시적으로 disk에 저장된다.
    추가적으로, Spring Configuration에 'StandardServletMulipartResolver' Bean을 추가해야 한다.
    이는 Serlvet 3.0 'javax.servlet.http.Part API 기반 'MultipartResolver' interface의 표준 구현체이다.

    요구된 MultiPartConfigElement를 DispatcherServlet에 등록하기 위해서  어떻게 customizeRegistration을 override를 하는 지에 주목해야 한다.
    또한 HelloWorldConfiguration.class에 multipart support를 활성하기 위한 'StandardServletMultipartResolver의 bean을 등록한다.
 **/