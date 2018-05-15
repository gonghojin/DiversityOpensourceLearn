package com.gongDel.spring;

import com.gongDel.spring.configuration.AppConfig;
import com.gongDel.spring.service.FileService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class AppMain {

    public static void main(String args[]){
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        FileService service = (FileService) context.getBean("fileService");

        service.readValues();
        context.close();
    }

}
