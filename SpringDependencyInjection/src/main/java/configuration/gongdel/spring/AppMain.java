package configuration.gongdel.spring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMain {
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        Communication app = (Communication) context.getBean("communication");
        app.comunicate();
    }
}
