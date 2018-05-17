package com.gongdel.spring.configuration;

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

@Configuration // 이 클래스가 Spring container가 관리하는 @Bean을 사용하고 있는 하나 이상의 methods를 포함한다는 것을 가리킴
@EnableTransactionManagement // == Spring's tx:* XML namespace, Spring's annotation-driven transaction 관리를 가능하게 해줌
@ComponentScan({ "com.gongdel.spring.configuration"}) // == <context:component-scan base-package="..." /> in xml
                                                        // Spring에 관리되는 beans/classes가 어디 있는지를 제공함
@PropertySource(value = { "classpath:application.properties"})
public class HibernateConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.gongdel.spring.model"} );
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
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format.sql"));

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
        @PropertySource는  Spring run-time Environment에 properties설정(application classpath 안에 있는 properties file에 있는)을 선언하기 위해서 사용

        Method sessionFactory()는 XML 기반 configuration을 반영하고 있는 LocalSessionFactoryBean을 생성함
        우리는 dataSource와 hibernate properties(hibernate.properties와 같은)이 필요한데
        @Properties 덕분에, .properties file 안에 있는 실제 values를 표면화할 수 있고, 항목에 일치하는 값을 인출하기 위한 Spring's Environment를 사용할 수 있다.
        해당 항목들은 Bean method인 transactionManager에 injected 된다.
        transactionManager은 최종적으로 sessionFactory에 의해서 생성되는 sessions을 위한 transaction support(지원)를 제공할 것이다.
     **/

    /** XML 환경설정과 비교

     <context:property-placeholder location="classpath:application.properties" />

     <context:component-scan  base-package="com.websystique.spring" />

     <tx:annotation-driven transaction-manager="transactionManager"/>

     <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
         <property name="driverClassName" value="${jdbc.driverClassName}" />
         <property name="url" value="${jdbc.url}"/>
         <property name="username" value="${jdbc.username}" />
         <property name="password" value="${jdbc.password}"/>
     </bean>

     <bean id="sessionFactory" class="org.springframework.orm.hibernate4.LocalSessionFactoryBean" >
         <property name="dataSource" ref="dataSource"/>
         <property name="packagesToScan">
             <list>
                <value>com.websystique.spring.model</value>
             </list>
         </property>
         <property name="hibernateProperties">
             <props>
                 <prop key="hibernate.dialect">${hibernate.dialect}</prop>
                 <prop key="hibernate.show_sql">${hibernate.show_sql:false}</prop>
                 <prop key="hibernate.format_sql">${hibernate.format_sql:false}</prop>
             </props>
         </property>
     </bean>

     <bean id="transactionManager"  class="org.springframework.orm.hibernate4.HibernateTransactionManager">
         <property name="sessionFactory" ref="sessionFactory" />
     </bean>

     <bean id="persistenceExceptionTranslationPostProcessor"
     class="org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor"/>

     **/