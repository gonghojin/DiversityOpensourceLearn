package com.gongdel.springmvc.controller;

import com.gongdel.springmvc.model.Employee;
import com.gongdel.springmvc.service.EmployeeService;
import org.joda.time.LocalDate;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class AppControllerTest {

    @Mock
    EmployeeService service;

    @Mock
    MessageSource message;

    @InjectMocks
    AppController appController;

    @Spy
    List<Employee> employees = new ArrayList<Employee>();

    @Spy
    ModelMap model;

    @Mock
    BindingResult result;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        employees = getEmployeeList();
    }

    @Test
    public void listEmployees() {
        when(service.findAllEmployees()).thenReturn(employees);
        Assert.assertEquals(appController.listEmployees(model), "allemployees");// spy의 model 객체를 컨트롤러에 보내서 비교를 위에 담아오는 듯? 두번째 인자값은 return 값
        Assert.assertEquals(model.get("employees"), employees);// model에 담아온 employees를
        verify(service, atLeastOnce()).findAllEmployees();
    }

    @Test
    public void newEmployee() {
        Assert.assertEquals(appController.newEmployee(model), "registration");
        Assert.assertNotNull(model.get("employee"));
        Assert.assertFalse((Boolean)model.get("edit"));
        Assert.assertEquals(((Employee)model.get("employee")).getId(), 0); // Employee 객체의 id 속성은  0부터 시작(auto_increment)
    }

    @Test
    public void saveEmployeeWithValidationError() {
        when(result.hasErrors()).thenReturn(true);
        doNothing().when(service).saveEmployee(any(Employee.class));
        Assert.assertEquals(appController.saveEmployee(employees.get(0), result, model), "registration");
    }

    public List<Employee> getEmployeeList(){
        Employee e1 = new Employee();
        e1.setId(1);
        e1.setName("Axel");
        e1.setJoiningDate(new LocalDate());
        e1.setSalary(new BigDecimal(10000));
        e1.setSsn("XXX111");

        Employee e2 = new Employee();
        e2.setId(2);
        e2.setName("Jeremy");
        e2.setJoiningDate(new LocalDate());
        e2.setSalary(new BigDecimal(20000));
        e2.setSsn("XXX222");

        employees.add(e1);
        employees.add(e2);
        return employees;
    }
}

/**
    AppController 열어보면, AppController는 기본적으로 EmployeeService, MessageSource, Employee, ModelMap & BindingResult들의 각각의 역할을 이행하기 위해 의존하는 걸 볼 수 있다.
    각각의 AppController method들은 실제 작업을 하기 위해서 오직 이 객체들을 사용한다.

    따라서 AppController를 test하기 위해서, 이러한 dependencies들의 제공이 필요하다.  우리는 예제에서 Mockito framework를 사용하고 있다.
    우리는 '@Mock' annotation을 위에 적용함으로써 EmployeeService & MessageSource의 제공한다.
    또한 '@Spy' annotation을 적용함으로써 ModelMap, BindingResult & Employee의 'spy' object를 제공한다.

    Mockito의 '@Mock' object는 실제 instance가 아니라는 것을 이해하는 것이 중요하다. 이들은 type의 Class를 사용하여 생성된 instance의 단순한 뼈이다.
    그러나 이들의 주요한 능력은 그들이 수행한 모든 상호작용을 기억하는 것이다.

    반면 @Spy 객체는 실제 instance이다. 그러나 수행한 모든 상호작용을 기억하지는 못 한다.

    '@InjectMocks'는 class의 instance를 생성하고 mocks(@Mock, @Spy, @Captor, @InjectMocks에 의해 생성된)를 그 안에 주입한다.

    MockitoAnnotations.initMocks(this);는 Mockito annotation[@Mock, @Spy, @Captor, @InjectMocks]로 annotated 되어진 objects를 초기화한다.

    Mockito annotation을 사용할 때는 'MockitoAnnotations.initMocks'를 호출해야 한다. 그렇지 않으면 이러한 mocks들이 테스트에 사용되어지지 않는다.

    '@Test & '@BeforeClass'은 TestNG의 특정 annotation이다.

    'Assert'는 예상 결과값과 실제 결과값에 대한 주장을 하기 위한 TestNG api이다.

    when..then & verify 는 테스트에서 동작을 정의하고 선택적으로 동작이 실제로 실행되었는지 확인하는 데 자주 사용되는 스텁 및 확인 기술입니다
 * **/


/** 더 자세한 설명 http://websystique.com/java/testng-tutorial/ **/