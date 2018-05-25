package configuration.gongdel.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Profile("Development")
@Configuration
public class DevDatabaseConfig implements DatabaseConfig {

    @Override
    @Bean
    public DataSource createDataSource() {
        System.out.println("Creating DEV database");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // Set MySQL specific properties for Development Environment

        return dataSource;
    }
}

/*
    XML CONFIG
        <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
            <property name="driverClassName" value="com.mysql.jdbc.Driver" />
            <property name="url" value="jdbc:mysql://localhost:3306/selftStudy" />
            <property name="username" value="selfStudy" />
            <property name="password" value="selfStudyPw"
        </bean>

 */