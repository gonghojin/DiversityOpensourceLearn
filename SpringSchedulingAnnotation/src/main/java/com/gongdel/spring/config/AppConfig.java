package com.gongdel.spring.config;

import com.gongdel.spring.scheduling.MyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class AppConfig {

    @Bean
    public MyBean bean() {
        return new MyBean();
    }

    /**
     *  @EnableScheduling은 Spring의 scheduled task execution(실행)을 가능하게 해준다. (이전에 보았던 Spring's task namespace와 비슷한 기능)
     *  덕분에, @Scheduler가 annotated된 모든 bean method가 scheduling을 위해 등록된다.
     */
}

/**
    이전 포스트에서(XML 환경설정), 완료되기 오래걸리거나 빈번하게 사용하는 task는 thread-pool을 설정해야 한다고 언급했었다.
    그렇다면 Annotation 환경설정 방법은?

    위의 과정은 기본 thread-pool의 scheduling이므로, 추가적인 코드 필요
    1. SchedulingConfigurer 인터페이스 구현 ( ~~ AppConfig implements SchedulingConfigurer)
    2. configureTasks() 메소드 override

        @Override
        pulbic void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
          taskRegistrar.setScheduler(taskExecutor());
        }

        @Bean(destoyMethod="shutdown")
        public Executor taskExecutor() {
            return Executors.newScheduledThreadPool(10);
        }



 **/