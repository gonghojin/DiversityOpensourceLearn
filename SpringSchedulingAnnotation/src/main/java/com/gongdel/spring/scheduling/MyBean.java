package com.gongdel.spring.scheduling;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

public class MyBean {

    @Scheduled(fixedRate=5000)
    public void printMessage() {
        System.out.println("I am called by Spring scheduler");
    }

    /**
     *  위 @Scheduled anoatated methodsms Scheduler에 의해서 5초마다 호출되어 질 것이다.
     *  주의) @Scheduler가 annotated 되어진 method는 void를 리턴해야만 하고, parameter를 가질 수 없다.
     *  물론, printMessage 안에 호출되어지는 외부 기능을 얻기 위해서, 위의 bean 안에 다른 bean을 inject할 수 있다.
     *
     *  @Scheduled annotation은 다른 scheduling timelines을 명시하기 위한 몇 개의 attributes를 가지고 있다.
     *     initialDelay : 첫번째 method의 실행 전에 대기하기 위한 시간을 millisecond 단위로 명시한다.
     *     fixedRate : method를 완료하는 데 걸리는 시간과 관계없이 각각의 method 시작 사이의 밀리 초 수를 지정합니다.
     *                  이는 이전 run의 완료와 다음 run의 시작 사이를 millisecond 단위로 명시할 수 있다.
     *     cron : 더 미세한 task 실행 제어기능을 제공한다.
     *
     */

}
