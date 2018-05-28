package com.gongdel.springmvc.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.gongdel.springmvc")
public class AppConfig {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");

        return messageSource;
    }
}
/**
    @EnableWebMvc는 XML의 mvc:annotation-driver과 동일하다.
    Method viewResolver는 실제 view를 식별하기 위한 view resolver를 환경설정한다.

    이 예제에서, 우리는 form 전송을 작업하고 있으며 사용자의 입력을 유효성 검사한다(JSR303 annotations을 통해).
    유효성 검사가 실패한 경우에, 기본적인 error 메시지가 보여지는데,
    이 기본 메시지를 외부의 Message bundle[.properties file]로 국제화된 메시지로 재정의 하기 위해서, 'ResourceBundleMessageSource'를 환경설정 해야한다.
    Method 'messageSource'가 이 목적으로 있다. 주의할 점은 basename method에 매개변수 (messages)를 제공해야 한다.
    그러면 Spring은 application class path 안에 있는 messages.properties라는 이름의 file을 찾을 것이다.
 **/