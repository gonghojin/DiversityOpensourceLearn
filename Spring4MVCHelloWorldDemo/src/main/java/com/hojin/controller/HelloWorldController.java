package com.hojin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")//위에 잡으면 공통적은 url 주소 
public class HelloWorldController {
	
	@RequestMapping(method = RequestMethod.GET)//value 값 생략 시, 공통url 주소에만 반응
	public String sayHello(ModelMap model){
		System.out.println("확인중ㄴ");
		model.addAttribute("greeting", "Hello Hojin World");
		return "welcom";// return 값은 view reselver(servlet.xml)에 정의된 suffix와 prefix가 붙음
		
	}
	
	 
    @RequestMapping(value="/helloagain", method = RequestMethod.GET)
    public String sayHelloAgain(ModelMap model) {
        model.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
        return "welcome";
    }

}
