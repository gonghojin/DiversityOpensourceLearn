package com.gongdel.springmvc.controller;

import com.gongdel.springmvc.model.Employee;
import com.gongdel.springmvc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    EmployeeService service;

    @Autowired
    MessageSource messageSource;

    // all list Employee
    @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public String listEmployees(ModelMap model) {

        List<Employee> employees = service.findAllEmployees();
        model.addAttribute("employees", employees);

        return "registration";
    }

    // add Employee - GET
    @RequestMapping(value = { "/new" }, method = RequestMethod.GET)
    public String newEmployee(ModelMap model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        model.addAttribute("edit", false); // 추가 & 수정 페이지 구분

        return "registration";
    }

    // 추가 - POST - Employee
    // 사용자 입력값 유효성검사
    @RequestMapping(value = { "/new" }, method = RequestMethod.POST)
    public String saveEmployee(@Valid Employee employee, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "registration";
        }

        /**
         *  field [ssn]의 유일성을 달성하기 위해 선호되는 방법은 custom @Unique annotation을 구현하고 Model class [Employee]의 field [ssn] 위에 적용한다.
         *
         *  아래에 언급되어진 [if block] 코드 부분은 validation 안에 custom errors를 채울지 입증한다.
         */
        if (!service.isEmployeeSsnUnique(employee.getId(), employee.getSsn())) {
            FieldError ssnError = new FieldError("employee", "ssn", messageSource.getMessage("non.unique.ssn", new String[]{employee.getSsn()}, Locale.getDefault()));
            result.addError(ssnError);

            return "registration";
        }

        service.saveEmployee(employee);

        model.addAttribute("success", "Employee " + employee.getName() + " registered successfully");
        return "success";
    }

    // 수정 - GET - Employee
    @RequestMapping(value = { "edit-{ssn}-employee" }, method = RequestMethod.GET)
    public String editEmployee(@PathVariable String ssn, ModelMap model) {
        Employee employee = service.findEmployeeBySsn(ssn);
        model.addAttribute("employee", employee);
        model.addAttribute("edit", true);

        return "registration";
    }

    // 수정 - POST - Employee
    @RequestMapping(value = { "edit-{ssn}-employee" }, method = RequestMethod.POST)
    public String updateEmployee(@Valid Employee employee, BindingResult result, ModelMap model, @PathVariable String ssn) {
        if (result.hasErrors()) {
            return "registration";
        }

        if(!service.isEmployeeSsnUnique(employee.getId(), employee.getSsn())) {
            FieldError ssnError = new FieldError("employee", "ssn", messageSource.getMessage("non.unique.ssn", new String[]{employee.getSsn()}, Locale.getDefault()));
            return  "registration";
        }

        service.updateEmployee(employee);

        model.addAttribute("success", "Employee " + employee.getName() + " updated successfully");

        return "success";
    }

    //삭제 - GET - Employee
    @RequestMapping(value = { "/delete-{ssn}-employee" }, method = RequestMethod.GET)
    public String  deleteEmploye(@PathVariable String ssn) {
        service.deleteEmployeeBySsn(ssn);
        return "redirect://list";
    }
}

/**
 *  꽤 직관적인 Spring 기반 controller이다. @Controller는 "이 class는 @RequestMapping에 의해 mapped 되어지는 pattern을 갖춘 request(요청)를 처리하는" controller임을 나타낸다.
 여기서 "/"는 기본 controller 역할을 한다.

 Method 'listEmployees'는 @RequestMethod.GET이 annotated 되어졌고, 기본 URL '/'뿐만 아니라 '/list'를 처리한다
 이는 application 초기 page의 handle 역할을 하며, 존재하는 employees의 list를 보여준다.

 Method 'newEmployee'는  새로운 employee 등록 page(Employee 객체 model이 지원하는)를 위한 GET request를 처리한다.

 Method 'saveEmployee'는 '@RequestMethod.POST'가 annotated 되어지고, 새로운 employee 등록("/new")을 위한 form-submission(전송) POST 요청을 처리한다.
 주목할 점은 parameter와 method 안에 있는 명령문이다. '@Valid'는 spring에게 연관된 객체(Employee)의 유효성 검사를 요청한다.
 '@BindingResult'는 이 validation의 출력과 validation(유효성 검사) 동안 발생한 error를 포함한다.
 BindingResult는  validated 객체 바로 다음에 와야하는 것을 주의해야 한다. 그렇지 않으면 validate가 이루어지지 않으며 exception 처리 된다.
 validation이 실패한 경우에, custom error message(우리가 환경설정한)가 보여진다.

 우리는 또한 SSN 속성을 database 안에 유일한 값을 같도록 선언했기 때문에 SSN의 유일성 체크를 위한 code를 포함했다.
 employee를 저장하거나 업데이트하기 전에, SSN이 유일한 값인지 체크한다. 만약 그렇지 않다면, 우리는 validation error를 발생시키고 registration page로 redirect한다.
 이 code 부분은 국제화 메시지를 사용하는 동시에 validation framework 외부에서 custom error를 채우기 위한 방법을 예증하다.

 Method 'editEmployee'는 세부적으로 채워진 employee 객체를 보여주는 registration page로 이동하고.
 Method 'updateEmployee'는 gui에서 가능한 업데이트 후에 update button을 눌렀을 때 호출된다.

 Method 'deleteEmployee'는 SSN 값으로 찾아진 employee 객체를 처리한다.
 주목할 부분은 URI template 변수(우리의 경우 SSN)에 바인딩 되어지는 paramether를 나타내는 '@PathVariable'이다.

 */
