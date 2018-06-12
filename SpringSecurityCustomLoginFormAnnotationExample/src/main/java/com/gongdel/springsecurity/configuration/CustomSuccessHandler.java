package com.gongdel.springsecurity.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// 3차 추가
@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(authentication);
    }

    protected String determineTargetUrl(Authentication authentication) {
        String url = "";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();

        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }

        if (isDba(roles)) {
            url = "/db";
        } else if (isAdmin(roles)) {
            url = "/admin";
        } else if (isUser(roles)) {
            url = "/home";
        } else {
            url = "/accessDenied";
        }

        return url;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    private boolean isUser(List<String> roles) {
        if (roles.contains("ROLE_USER")) {
            return true;
        }

        return false;
    }

    private boolean isAdmin(List<String> roles) {
        if (roles.contains("ROLE_ADMIN")) {
            return true;
        }
        return false;
    }

    private boolean isDba(List<String> roles) {
        if (roles.contains("ROLE_DBA")) {
            return true;
        }
        return false;
    }

}
/**
    주목할 점은 우리는 Spring의 'SimpleUrlAuthenticationSuccessHandler' class를 extend하고 'handle()' method를 override 했다.
    handle() method는  사용자 정의된 'determineTargetUrl' method에 의해서 return 되어지는 URL과 함께 구성된 'RedirectStrategy'
    [이 경우에는 default]를 사용하는 redriect를 포함한다. 이 method는 Authentication object로부터 최근에 로그인 된 user의 권한을 추출한 후,
    해당 권한에 맞는 적절한 URL을 생성한다.
    마지막으로 Spring Security framework 안에서 모든 redirection의 역할을 하는 RedriectStrategy는 특정된 URL에 요청을 redirect한다.

 **/