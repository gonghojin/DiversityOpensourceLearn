package configuration.gongdel.spring.scheduling;

import org.springframework.stereotype.Component;

@Component
public class MyBean {

    public void printMessage() {
        System.out.println("I am called by MethodInvokingJobDetailFactoryBean using SimpleTriggerFactoryBean");
    }
}
