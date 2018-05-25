package configuration.gongdel.spring.service;

import com.gongdel.spring.dao.EmployeeDao;
import com.gongdel.spring.model.Employee;
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
    public void saveEmployee(Employee employee) {
        dao.saveEmployee(employee);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return dao.findAllEmployee();
    }

    @Override
    public void deleteEmployeeBySsn(String ssn) {
        dao.deleteEmployeeSsn(ssn);
    }

    @Override
    public Employee findBySsn(String ssn) {
        return dao.findBySsn(ssn);
    }

    @Override
    public void updateEmployee(Employee employee) {
        dao.updateEmployee(employee);
    }
}

/**
   위에서 가장 흥미있는 부분은 @Transactional
   @Transactional은 각각의 method가 실행될 때 transcational을 실행하고, 끝났을 때 commit한다.
    (만약 error 떄문에 method가 실패를 할 경우 rollback)
   주목할 점은 transaction이 method scope 안에 그리고 우리가 사용하는 dao method 안에 있기 떄문에,
   DAO method는 같은 transaction으로 실행된다.
 **/