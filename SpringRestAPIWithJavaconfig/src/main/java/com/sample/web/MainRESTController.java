package com.sample.web;

import com.sample.dao.TestDao;
import com.sample.dao.employee.EmployeeDao;
import com.sample.dao.security.mapper.UserMapper;
import com.sample.domain.employee.Employee;
import com.sample.domain.security.User;
import com.sample.service.security.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final UserService userService;

    @GetMapping
    public User welcome() {
        return userService.readUser("cusonar");
    }

    @GetMapping(value = "/employees"/*, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public List<Employee> getEmployees() {
        TestDao testDao = sqlSession.getMapper(TestDao.class);
        System.out.println(testDao.test().getId() + " / " + testDao.test().getName());
        List<Employee> list = employeeDao.getAllEmployees();

        return list;
    }




}

