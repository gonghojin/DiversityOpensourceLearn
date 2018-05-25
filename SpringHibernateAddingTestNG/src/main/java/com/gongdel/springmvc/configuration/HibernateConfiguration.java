package com.gongdel.springmvc.configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.gongdel.springmvc.configuration" })
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.gongdel.springmvc.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));

        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);

        return txManager;
    }
}

/**
    @Configuration : 이 클래스가 @Bean(spring container에 의해 관리되어지는 bean을 생성함)이 annotated된 하나 이상의 bean method를 포함한다고 나타낸다.
          우리의 경우, 이 클래스는 hibernate configuration을 나타낸다.
    @ComponentScan : xml의 context:component-scan base-package="..."와 동일하며, spring이 관리하는 beans/classes가 어디서 찾는지를 제공한다.
    @EnableTransactionManagement : Spring의 tx:* XML namespace와 동일하며, Spring의 annotation-driven transaction management를 가능하게 해준다.
    @PropertySource : Spring run-time 환경에(Environment)에 properties(application classpath에 정의된 properties 파일)의 설정을 선언하며,
            다른 application 환경에 있는 다른 값을 가질 수 있는 유연성을 제공한다.

    sessionFactory() 메소드는 LocalSessionFactoryBean을 생성한다. 이는 XML 기반 환경설정을 정확하게 반영한다.
        : 우리는 dataSource와 hibernate properties가 필요하다.(hibernate.properties와 같은)
        @PropertySource 덕분에, 우리는  .properties 파일에 실제 값을 외부화할 수 있으며,
        Spring의 Environment를 사용하여 항목과 일치하는 값을 인출할 수 있다.
        일단 SessionFactory가 생성되면, Bean method인 transactionManager에 주입된다.
        이는 결국 sessionFactory에 의해 생성되어지는 session을 위한 transaction 지원을 제공한다.
 **/