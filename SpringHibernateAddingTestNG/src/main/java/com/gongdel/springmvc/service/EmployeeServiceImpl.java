package com.gongdel.springmvc.service;

import com.gongdel.springmvc.dao.EmployeeDao;
import com.gongdel.springmvc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao dao;

    @Override
    public Employee findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void saveEmployee(Employee employee) {
        dao.saveEmployee(employee);
    }

    /*
	method가 Transaction과 함께 실행되기 때문에, hibernate update를 호출할 필요가 없다.
	db로부터 entity를 인출하고 그 값을 transaction 내의 적절한 값으로 update한다.
	taransaction이 끝나면 해당 값이 db 안으로 update된
    */
    @Override
    public void updateEmployee(Employee employee) {
       Employee entity = dao.findById(employee.getId());
       if (entity != null) {
           entity.setName(employee.getName());
           entity.setJoiningDate(employee.getJoiningDate());
           entity.setSalary(employee.getSalary());
           entity.setSsn(employee.getSsn());
       }
    }

    @Override
    public void deleteEmployeeBySsn(String ssn) {
        dao.deleteEmployeeBySsn(ssn);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return dao.findAllEmployees();
    }

    @Override
    public Employee findEmployeeBySsn(String ssn) {
        return dao.findEmployeeBySsn(ssn);
    }

    @Override
    public boolean isEmployeeSsnUnique(Integer id, String ssn) {
        Employee employee = findEmployeeBySsn(ssn);

        return (employee == null || (id != null) && (employee.getId() == id));
    }
}
/* 위에서 흥미있는 부분은 각각의 method가 실행할 때 transaction을 시작하고, method가 종료될 때 commit을 하는(또는 error로 인해 실패할 경우 rollback) @Transactional이다.
	transaction은 method 범위에 있고, 우리가 사용하는 DAO method 안에 있기 때문에, DAO method는 동일한 transaction 안에서 실행된다.

*/
