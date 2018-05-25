package configuration.gongdel.spring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.gongdel.spring")
public class AppConfig {

    @Autowired
    public DataSource dataSource;

    /**
        위의 dataSource bean은 다른 환경(Development를 위한 Mysql data & Producttion을 위한 Oracle)의 beans을 주입한다.

     **/
}

