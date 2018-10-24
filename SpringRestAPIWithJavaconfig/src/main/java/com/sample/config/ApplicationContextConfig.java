package com.sample.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan("com.sample.*")
@PropertySource(value = {
        "file:/Users/gonghojin/Desktop/app/dev-application.properties"
})
public class ApplicationContextConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
