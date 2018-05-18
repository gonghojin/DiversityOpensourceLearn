package com.gongdel.spring;

public class Communication {

    private Messaging messaging;

    // Setter를 통한 DI


    public void setMessaging(Messaging messaging) {
        this.messaging = messaging;
    }

    public void comunicate() {
        messaging.sendMessage();
    }
}
    /**
        이 접근법에는, dependency가 Setter Method 사용에 의해 injected된다

        Property Setter 접근은 더 광범위하게 사용되고, 이해하기 쉽다.

     **/