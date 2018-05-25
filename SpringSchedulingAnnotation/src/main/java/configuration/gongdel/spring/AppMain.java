package configuration.gongdel.spring;

import com.gongdel.spring.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class AppMain {

    @SuppressWarnings({ "unused", "resource" })
    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    /**
     *  여기에 어떠한 scheduling class or method를 호출하지 않았다. 단지 Configuratio class만 등록했다.
     *  @EnableScheduling과 함께하는 configuration class를 annotated 했기 때문에, @Scheduler가 annotated 되어진 bean methods가 자동으로 등록된다.
     *
     *
     */
}

