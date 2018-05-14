package com.gongDel.spring.dao;

import com.gongDel.spring.model.Employee;
import org.springframework.stereotype.Repository;

@Repository("employeeDao") // 매개변수는 이 빈 클래스의 이름을 정의
public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public void saveInDatabase(Employee employee) {

       System.out.println("Employee "+employee.getName()+" is registered for assessment on "+ employee.getAssessmentDate());
    }
}
