package com.gongdel.demo;

import com.gongdel.demo.domain.payroll.Employee;
import com.gongdel.demo.domain.payroll.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(EmployeeRepository employeeRepository) {
        return (args) ->  employeeRepository.save(
                new Employee("Frodo", "Baggins", "ring bearer")
        );
    }
}

