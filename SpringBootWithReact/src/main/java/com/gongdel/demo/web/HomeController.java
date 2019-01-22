package com.gongdel.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/test")
    public String index() {
        /*
             Spring Boot의 자동 환경설정 view Resolver는 src/main/templates/~에 매핑해준다.
         */
        return "index";
    }
}
