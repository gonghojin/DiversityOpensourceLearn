package com.gongdel.spring.dao;

import com.gongdel.spring.model.Employee;

import java.util.List;

public interface EmployeeDao {

    void saveEmployee(Employee employee);

    List<Employee> findAllEmployee();

    void deleteEmployeeSsn(String ssn);

    Employee findBySsn(String ssn);

    void updateEmployee(Employee employee);

}
