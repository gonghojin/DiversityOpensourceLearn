package com.gongdel.spring;

import com.gongdel.spring.configuration.AppConfig;
import com.gongdel.spring.model.Employee;
import com.gongdel.spring.service.EmployeeService;
import org.joda.time.LocalDate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.math.BigDecimal;
import java.util.List;

public class AppMain {

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        EmployeeService service = (EmployeeService) context.getBean("employeeService");

        /*
         * Create Employee1
         */
        Employee employee1 = new Employee();
        employee1.setName("Han Yenn");
        employee1.setJoiningDate(new LocalDate(2010, 10, 10));
        employee1.setSalary(new BigDecimal(10000));
        employee1.setSsn("ssn00000001");

        /*
         * Create Employee2
         */
        Employee employee2 = new Employee();
        employee2.setName("Dan Thomas");
        employee2.setJoiningDate(new LocalDate(2012, 11, 11));
        employee2.setSalary(new BigDecimal(20000));
        employee2.setSsn("ssn00000002");

        /*
         * 두 개의 Employee 삽입
         */
        service.saveEmployee(employee1);
        service.saveEmployee(employee2);

        /*
         * 모든 employees list 불러오기
         */
        List<Employee> employees = service.findAllEmployees();
        for (Employee emp : employees) {
            System.out.println(emp);
        }

        /*
          employee 삭제

         */
        service.deleteEmployeeBySsn("ssn00000002");


        /*
          employee 업데이트

         */
        Employee employee = service.findBySsn("ssn00000001");
        employee.setSalary(new BigDecimal(50000));
        service.updateEmployee(employee);

        //모든 employee 리스트
        List<Employee> employeeList = service.findAllEmployees();
        for (Employee emp : employeeList) {
            System.out.println(emp);
        }

        context.close();
    }
}

    /**
     *  만약 AppConfig를 제거한다면?
     *
     *      AnnoationConfigApplication context = new AnnotationConfigApplicationContext();
     *      context.scan("com.gongdel.spring");
     *      context.refresh();
     *
     *      ~~
     *
     * */