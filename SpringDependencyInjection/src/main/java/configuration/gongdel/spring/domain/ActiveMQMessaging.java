package configuration.gongdel.spring.domain;

public class ActiveMQMessaging implements Messaging {

    public void sendMessage() {
        System.out.println("Sending Message via Active MQ");
    }
}
