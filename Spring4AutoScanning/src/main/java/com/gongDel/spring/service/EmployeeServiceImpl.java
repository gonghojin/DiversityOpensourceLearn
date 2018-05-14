package com.gongDel.spring.service;

import com.gongDel.spring.dao.EmployeeDao;
import com.gongDel.spring.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired// Spring context에 잡힌 적절한 bean이 Spring's DI에 의해서 auto-wired됨
    private DateService dateService;

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public void registerEmployee(Employee employee) {
        employee.setAssessmentDate(dateService.getNextAssessmentDate());
        employeeDao.saveInDatabase(employee);
    }
}
