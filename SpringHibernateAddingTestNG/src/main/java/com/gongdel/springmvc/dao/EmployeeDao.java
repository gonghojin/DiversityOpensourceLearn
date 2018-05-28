package com.gongdel.springmvc.dao;

import com.gongdel.springmvc.model.Employee;

import java.util.List;

public interface EmployeeDao {

    Employee findById(int id);

    void saveEmployee(Employee employee);

    void deleteEmployeeBySsn(String ssn);

    List findAllEmployees();

    Employee findEmployeeBySsn(String ssn);
}
