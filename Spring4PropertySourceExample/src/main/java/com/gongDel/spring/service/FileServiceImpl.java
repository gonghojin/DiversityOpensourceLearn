package com.gongDel.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service("fileService")
public class FileServiceImpl implements FileService {

    @Value("${sourceLocation:c:/temp/input}")
    private String source;

    @Value("${destinationLocation:c:/temp/output}")
    private String destination;

    @Autowired
    private Environment environment; // Spring에 의해서 auto-wired를 얻음
                                     // @PropertiSource annotation 덕분에, 이 Environment는 명시된 .properties file에 선언된 모든 속성들에 접근 가능

    public void readValues() {
        System.out.println("Getting property via Spring Environment :"
                + environment.getProperty("jdbc.driverClassName")); // getProperty method를 사용해서 구체적인 속성값을 얻을 수 있음

        System.out.println("Source Location : " + source);
        System.out.println("Destination Location : " + destination);

    }

}
    /**
        @Value annoation의 형태
            @Value("${key:default"})
            위의 선언은 Spring에게 key name이 'key'(properties file에 있는)인 속성을 찾으라고 지시함
            그리고 그의 값을 다양한 변수에 할당함. 만약 'key' 속성을 찾을 수 없다면 'default' 값을 다양한 변수에 할당함

            위의 ${...} placeholder는 오직 PropertySourcesPlaceholderConfigurer bean(AppConfig에 등록했던)을 등록했을 떄만 처리될 수 있다.
     **/