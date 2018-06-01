package com.gongdel.springsecurity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 *  우리의 application에 spring security를 추가하기 위한 가장 첫번째 단계는 'Spring Security Java Configuration'을 생성하는 것이다.
 *  이 Configuration는 springSecurityFilterChain으로 알려진 Sevlet Filter를 생성한다.
 *  springSecurityFilterChain은 우리의 application 안에 있는 모든 security(application URLs의 보호, 전달받은 username과 password의 유효성 검사, 로그인 form을 재지정 등등)를 담당한다.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("root123").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("dba").password("root123").roles("ADMIN", "DBA"); // dba는 두가지 권한 부여
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/homes").permitAll()
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                .and().formLogin()
                .and().exceptionHandling().accessDeniedPage("/Access_Denied");
    }
}
/**
    method configureGlobalSecurity는 사용자 자격과 허용 권한을 'AuthenticationManagerBuilder'로 환경설정한다.
    이 AuthenticationManagerBuild는  autentication(인증) request의 처리를 담당하는 'AuthenticationManager'를 생성한다.
    주목할점은 JDBC, LDAP 그리고 다른 인증을 자유롭게 선택할 수 있는 in-memory authentication의 사용이다.

    ovrride된 Method 'Configure'은 특정한 http request의 web 기반 보안을 위한 'HttpSecurity'를 환경설정한다.
    기본적으로 모든 request에 적용되지만, requestMathcer(RequestMatcher)/antMathcers 또는 다른 비슷한 method를 사용하여 제한할 수 있다.

    위의 configuration에서 URL '/' & '/home'은 보안되지 않아 누구나 접근할 수 있다.
    URL '/admin/**' 은 오직 ADMIN 권한을 가진 누군가만 접근할 수 있다.
    URL '/db/**은  오직 ADMIN 또는 DBA권한을 가진 누군가만 접근할 수 있다.

    Method 'formLogin'은 user 자격을 요청하는 기본 authentication form을 지원한다.
    또한 자기 자신만의 login form 설정도 가능하다.(다음 예제에서 다룰 것이다).

    우리는 경우에 따라서 모든 403 exception[http 접근 거부]을 catch 하고
    기본적인 HTTP 403 page[어떠한 도움이 되지 않음]를 보여주는 대신에 우리가 정의한 page를 보여줄 수 있는 'exceptionHandling().accessDeniedPage()를 사용한다.
 **/

/**
    <XML Configuration>

         <http auto-config="true" >
             <intercept-url pattern="/" access="permitAll" />
             <intercept-url pattern="/home" access="permitAll" />
             <intercept-url pattern="/admin**" access="hasRole('ADMIN')" />
             <intercept-url pattern="/dba**" access="hasRole('ADMIN') and hasRole('DBA')" />
             <form-login  authentication-failure-url="/Access_Denied" />
         </http>

         <authentication-manager >
             <authentication-provider>
                 <user-service>
                 <user name="bill"  password="abc123"  authorities="ROLE_USER" />
                 <user name="admin" password="root123" authorities="ROLE_ADMIN" />
                 <user name="dba"   password="root123" authorities="ROLE_ADMIN,ROLE_DBA" />
                 </user-service>
             </authentication-provider>
         </authentication-manager>
 **/