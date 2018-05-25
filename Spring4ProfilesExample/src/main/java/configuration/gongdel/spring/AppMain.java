package configuration.gongdel.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 활성화 profiles 설정
        context.getEnvironment().setActiveProfiles("Development");
        // 언급된 package[s]를 스캔하고 모든 @Component를 Spring에 등록
        context.scan("com.gongdel.spring");
        context.refresh();
        context.close();

    }

    /**
        실행 시 활성화될 profiles를 어떻게 설정하는지 주목해라.
        context.scan("package")는 업근된 package를 스캔하고 모든 @Compoent를 등록한다.
        그러나, bean/configuration 위에 @Profile annotation을 만날 때, environment안에 잡힌 profile value와 비교를 한다.
        만약 value가 일치한다면, 해당 bean을 등록하고 그렇지 않다면 건너뛴다.
        우리의 경우는  DevDatabaseConfig dataSource가 Appconfig.dataSource 안에 주입될 것이다.

     **/

    /**
        XML Configuration(app-config.xml)

            <context:component-scan base-package="com.gongdel.spring" />

            <beans profile="Development">
                <import resources="dev-config-context.xml" />
            </beans>

            <beans profile="Production">
                <import resources="prod-config-context.xml" />
            </beans>


        AppMain.java

            ApplicationConfigurationContext context = new ApplicationConfigurationContext(app-config.xml);
            context.getEnvironment().setActiveProfiles("Development");

            ~~

     */
}
