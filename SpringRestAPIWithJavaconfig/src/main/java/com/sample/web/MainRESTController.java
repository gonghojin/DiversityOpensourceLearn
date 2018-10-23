package com.sample.web;

import com.sample.dao.TestDao;
import com.sample.dao.employee.EmployeeDao;
import com.sample.domain.employee.Employee;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class MainRESTController {

    private final EmployeeDao employeeDao;
    private final SqlSession sqlSession;

    @GetMapping
    public String welcome() {
        return "Welcom to the Jungle World";
    }

    @GetMapping(value = "/employees"/*, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public List<Employee> getEmployees() {
        TestDao testDao = sqlSession.getMapper(TestDao.class);
        System.out.println(testDao.test().getId() + " / " + testDao.test().getName());
        List<Employee> list = employeeDao.getAllEmployees();

       return list;
    }



}

