package configuration.gongdel.spring.scheduling;

import org.springframework.stereotype.Component;

@Component("myBean")
public class MyBean {

    public void printMessage() {
        System.out.println("I am called by Spring scheduleer");
    }
}
/**
 *  Scheduler Task bean은 Scheduler를 통해서 method의 호출이 이루어지는 일반적인 POJO bean이다.
 *
 *  주목할 점은 scheduler를 통해서 호출되는 method(이 예제에서는 printMessage)가 void를 return 해야 하며, 어떤 parameter도 가지지 말아야 한다.
 *  물론 위의 bean 안에, printMessage 안에서 호출되어지는 외부의 기능을 얻기 위해, 다른 bean을 inject할 수 있다.
 *
 * **/