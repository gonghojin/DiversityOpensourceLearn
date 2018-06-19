package com.gongdel.springsecurity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.gongdel.springsecurity"})
public class HelloWorldConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    RoleToUserProfileConverter roleToUserProfileConverter;

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    /**
     * CSS/ Javascript와 같은 static resources 제공하기 위한 ResourceHandlers를 환경설정
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    /**
     * Converter 사용을 위한 설정
     * 우리의 예제에서, string values[Roles]를 UserProfiles로 전환하기 위해서 필요하다.
     **/
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(roleToUserProfileConverter);
    }
}

/**
    이 클래스에서 흥미로운 점은 id를 Object로 전환하는 역할을 하는 converter의 등록이다.
    이는 JSP에서 one-to-many 관계를 처리하기 위해서 필요하다. User 생성할 때, User는 다수의 roles/userProfiles를 할당할 수 있다.
    또한 우리는 특정한 role/userProfile를 profile id 기반의 user에 연결하기 위해서 converter가 필요하다.

    <XML Configuration>
     <mvc:annotation-driven conversion-service="conversionService"/>

     <bean id="conversionService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">

         <property name="converters">
             <list>
                <bean id="roleToUserProfile" class="com.gongdel.springsecurity.configuration.RoleToUserProfileConverter" />
             </list>
         </property>
     </bean>
 **/