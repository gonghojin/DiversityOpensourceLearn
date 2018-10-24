package com.sample.config;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@MapperScan("com.sample.dao")
public class DatabaseConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${mysql.class}")
    private String mysqlClass;
    @Value("${mysql.url}")
    private String mysqlUrl;
    @Value("${mysql.id}")
    private String mysqlId;
    @Value("${mysql.pw}")
    private String mysqlPw;



    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName(mysqlClass);
        source.setUrl(mysqlUrl);
        source.setUsername(mysqlId);
        source.setPassword(mysqlPw);

        return source;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis/config/mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/mapper/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }


    @Bean
    public SqlSession sqlSession() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
        return sqlSessionTemplate;
    }
}
