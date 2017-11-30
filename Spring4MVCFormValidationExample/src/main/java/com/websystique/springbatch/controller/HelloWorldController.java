package com.websystique.springbatch.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.websystique.springbatch.model.Student;

@Controller
@RequestMapping("/")
public class HelloWorldController {
	
	//기본적인 Get handler 역할
	@RequestMapping(method = RequestMethod.GET)
	public String newRegistration(ModelMap model){
		
		Student student = new Student();
		model.addAttribute("student", student);
		
		return "enroll";
	}
	
	/*
	 * form submission에 호출될 메소드
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String saveRegistration(@Valid Student student, BindingResult result, ModelMap model){
		
		if(result.hasErrors()){
			return "enroll";
		}
		
		model.addAttribute("success", "Dear " + student.getFirstName() + ",your Registration completed successful");
		return "success";
		
	}
}
