package com.websystique.springmvc.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.websystique.springmvc.model.Employee;
import com.websystique.springmvc.service.EmployeeService;

@Controller
@RequestMapping("/")
public class AppController {
	@Autowired
	EmployeeService service;
	
	@Autowired
	MessageSource messageSource;
	
	//employees 리스트
	@RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
	public String listEmployees(ModelMap model){
		
		List<Employee> employees = service.findAllEmployees();
		
		model.addAttribute("employees", employees);
		
		return "allemployees";
	}
	
	//employee 등록 Form
	@RequestMapping(value = {"/new"}, method = RequestMethod.GET)
	public String newEmployee(ModelMap model){
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		model.addAttribute("edit", false);
		
		return "registration";
	}
	
	//employee 등록- db 저장 및 유효성 검사
	@RequestMapping(value = {"/new"}, method = RequestMethod.POST)
	public String saveEmployee(@Valid Employee employee, BindingResult result, ModelMap model){
		
		if(result.hasErrors()){
			return "registration";
		}
		
		/* [ssn] 필드의 유일성을 만족하기 위한  선호되어지는 방법은  custom @Unique annotion을 구현하는 것이다.
		 * 그리고 Model class[Employee]의 필드[ssn]에 적용시킬 것이다.
		 * 
		 * 아래에서 언급된[if 구간] 부분은, 외부 validation framework에 custom errors 채울 수 있는 것을 보여준다.
		 * 뿐만 아니라 동시에 internationalized messages를 사용할 수 있다.
		 */
		
		if(!service.isEmployeeSsnUnique(employee.getId(), employee.getSsn())){
			FieldError ssnError = new FieldError("employee", "ssn", messageSource.getMessage("non.unique.ssn", new String[]{employee.getSsn()}, Locale.getDefault()));
			result.addError(ssnError);
			return "registration";
		}
		
		service.saveEmployee(employee);
		
		model.addAttribute("success", "Employee " + employee.getName() + " registered successfully");
		return "success";
	}
	
	//employee 업데이트 Form
	@RequestMapping(value = {"/edit-{ssn}-employee"}, method = RequestMethod.GET)
	public String editEmployee(@PathVariable String ssn, ModelMap model){
		Employee employee = service.findEmployeeBySsn(ssn);
		model.addAttribute("employee", employee);
		model.addAttribute("edit", true);
		return "registration";
	}
	
	//employee 업데이트- db 저장 및 유효성 검사
	@RequestMapping(value = {"/edit-{ssn}-employee"}, method = RequestMethod.POST)
	public String updateEmployee(@Valid Employee employee, BindingResult result, ModelMap model, @PathVariable String ssn){
		if(result.hasErrors()){
			return "registration";
		}
		
		if(!service.isEmployeeSsnUnique(employee.getId(), employee.getSsn())){
			FieldError ssnError = new FieldError("employee", "ssn", messageSource.getMessage("non.unique.ssn", new String[]{employee.getSsn()}, Locale.getDefault()));
			result.addError(ssnError);
			return "registration";
		}
		
		service.updateEmployee(employee);
		return "success";
	}
	
	//employee 삭제
	@RequestMapping(value = {"/delete-{ssn}-employee"}, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable String ssn){
		service.deleteEmployeeBySsn(ssn);
		return "redirect:/list";
	}
}
