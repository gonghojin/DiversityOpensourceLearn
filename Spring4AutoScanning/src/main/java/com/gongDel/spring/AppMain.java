package com.gongDel.spring;

import com.gongDel.spring.configuration.AppConfig;
import com.gongDel.spring.model.Employee;
import com.gongDel.spring.service.EmployeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class AppMain {

    public static void main(String[] args) {

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        /**
         *  만약 AppConfig.class를 제거한다면?
         *
         *   AnnoationConfigApplicationContext context = new AnnotationConfigApplicationContext();
         *   context.scan("com.gongDel.spring"); // 특정한 pacakge[s] 안에 있는 모든 클래스들을 스캔하고, @Component가 annotated된 bean을 등록
         *   context.refresh(); // 주의 ! - scan 후에 등록된 클래스들을 완벽히 처리하기 위해서 반드시 refresh()를 호출해야함
         *
         *
         *  XML 환경설정 시
         *
         *   AbstractApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");
         * **/

        EmployeeService service = (EmployeeService) context.getBean("employeeService");


        Employee employee = new Employee();
        employee.setName("GongDel");
        service.registerEmployee(employee);

        context.close();

    }
}
