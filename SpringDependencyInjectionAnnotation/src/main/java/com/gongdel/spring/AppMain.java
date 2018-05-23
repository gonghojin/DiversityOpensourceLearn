package com.gongdel.spring;

import com.gongdel.spring.config.AppConfig;
import com.gongdel.spring.domain.Application;
import com.gongdel.spring.domain.Bond;
import com.gongdel.spring.domain.Driver;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;


public class AppMain {

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Byname Autowiring
        Application application = (Application) context.getBean("application");
        System.out.println("Application Details : " + application); // Application Details : Application{user=ApplicationUser{name='defaultName'}}

        // Field + Constructor + Method Autowired
        Driver driver = (Driver) context.getBean("driver");
        System.out.println("Driver Details : " + driver); // Driver Details : Driver{license=License{number='123456ABC'}}

       // @Qualifier
       Bond bond = (Bond) context.getBean("bond");
       bond.showCar(); // This is Mustang

    }
}
