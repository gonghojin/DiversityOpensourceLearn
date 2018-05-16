package com.gongdel.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Profile("Production")
@Configuration
public class ProductionDataBaseConfig implements DatabaseConfig {

    @Override
    @Bean
    public DataSource createDataSource() {
        System.out.println("Creating Production database");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // Set ORACLE specific properties for Production environment
        return dataSource;
    }
}

/**
    DevDatabaseConfig와 ProductionDatabaseConfig는 DatabaseConfig interface를 implement하고 있는 간단한 configuration classes이다.
    이 클래스들의 특별한 점은 @Profile annotation을 annotated하고 있다는 것이다.

    component 위에 @Profile annotation은 오직 해당 profile이 활성화됐을 때, Spring context에 그 component를 등록한다.
    Profile의 활동은 밑의 설정에 의해서 해당 profile 값을 이용할 수 있다는 것을 의미한다.

      1. spring.profiles.active 속성(JVM arguments, environment 변수(variable) 또는 web application의 경우 web.xml에 있는 Servlet context parameter)
      2. ApplicationContext.getEnvironment().setActiveProfiles("ProfileName");

     예제는 environment 기반이기 때문에 profile value와 Spring container 안에 등록될 profile에 dependent된 beans을 제공할 수 있다.
 **/