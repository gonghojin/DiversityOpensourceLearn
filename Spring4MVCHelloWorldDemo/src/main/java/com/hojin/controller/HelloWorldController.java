package com.hojin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")//���� ������ �������� url �ּ� 
public class HelloWorldController {
	
	@RequestMapping(method = RequestMethod.GET)//value �� ���� ��, ����url �ּҿ��� ����
	public String sayHello(ModelMap model){
		System.out.println("Ȯ���ߤ�");
		model.addAttribute("greeting", "Hello Hojin World");
		return "welcom";// return ���� view reselver(servlet.xml)�� ���ǵ� suffix�� prefix�� ����
		
	}
	
	 
    @RequestMapping(value="/helloagain", method = RequestMethod.GET)
    public String sayHelloAgain(ModelMap model) {
        model.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
        return "welcome";
    }

}
