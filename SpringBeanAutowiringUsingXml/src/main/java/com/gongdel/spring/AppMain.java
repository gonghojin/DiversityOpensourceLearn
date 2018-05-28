package com.gongdel.spring;

import com.gongdel.spring.domain.Application;
import com.gongdel.spring.domain.Driver;
import com.gongdel.spring.domain.Employee;
import com.gongdel.spring.domain.Performer;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class AppMain {
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");

        // autowire="byName"
        Application application = (Application) context.getBean("application");
        System.out.println("Application Details : " + application);

        // autowire="byType"
        Employee employee = (Employee) context.getBean("employee");
        System.out.println("Employee Details : " + employee);

        // autowire="constructor"
        Performer performer = (Performer) context.getBean("performer");
        System.out.println("Performer Details : " + performer);

        // autowire="no"
        Driver driver = (Driver) context.getBean("driver");
        System.out.println("Driver Details : " + driver);
    }
}
