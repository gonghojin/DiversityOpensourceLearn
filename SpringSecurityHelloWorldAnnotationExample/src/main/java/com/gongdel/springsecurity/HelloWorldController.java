package com.gongdel.springsecurity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HelloWorldController {

    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
    public String homepage(ModelMap model) {
        model.addAttribute("greeting", "Hi, Welcome to mystie. ");

        return "welcome";
    }
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());

        return "admin";
    }

    @RequestMapping(value = "/db", method = RequestMethod.GET)
    public String dbPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());

        return "dba";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "welcome";
    }

    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());

        return "accessDenied";
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }

        return userName;
    }

}
/**
    Method getPrincipal()은 Spring 'SecurityContext'에서 로그인된 user name을 리턴하는 포괄적인 함수이다.

    Method logoutPage()는 SecurityContextLogoutHandler().logout(request, response, auth)에 간단한 호출로 로그아웃을 처리한다.
    이는 JSP 안에 하는 복잡한 logout logic의 코딩이 필요없기 떄문에 유용하고 편리하다.

   URL '/login'을 빠트렸다고 생각할 수 있는데, 이는 Spring Security에 의해 기본적으로 생성되고 처리되기 떄문이다.
 **/