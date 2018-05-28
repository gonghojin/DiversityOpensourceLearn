package com.gongdel.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.gongdel.spring")
public class AppConfig {
}

/**
 *  @ComponentScan은 특정된 package의 scanning을 통해서, Spring이 annotated bean들을 자동적으로 탐색하게 해준다.
 *  그리고 @Resource 또는 @Autowired의 사용을 통해,  필요할 때마다 어디서든 wire 시켜준다.
 *
 *  XML 설정 :
 *
 *      <context:component-scan base-package="com.gongdel.spring" />
 *
 * **/