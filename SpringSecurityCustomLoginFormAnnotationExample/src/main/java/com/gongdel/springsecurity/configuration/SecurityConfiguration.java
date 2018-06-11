package com.gongdel.springsecurity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

   /* in-memory authentication - database 저장 x
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("root123").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("dba").password("root123").roles("ADMIN", "DBA");
    }*/

    /** Feat : hibernate
     * Hibernate 사용 database 저장
     *
     *  'org.springframework.security.core.userdetails.UserDetailsService' 구현을 통해서
     *   모든 자격은 database에 저장되고, Spring Security에 접근 할 수 있다.
     *   우리는 결국 database의 data에 접근하기 위해 정의된 userService로, 완전한 transactional user를 사용하는 'UserDetailsService'의 구현체를 제공한다.
     *
     */

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/home").permitAll()
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                .and().formLogin().loginPage("/login")
                .usernameParameter("ssoId").passwordParameter("password")
                .and().csrf()
                .and().exceptionHandling().accessDeniedPage("/Access_Denied");
    }

}
/**
    위의 클래스는 이 전 예제(SpringSecurityHelloWorldAnnotationExample)와 모두 같지만, 한 가지 다른 점이 있다.
        .and().formLogin().loginPage("/logion")
                                               .usernameParameter("ssoId").passwordParameter("password")
                                               .and().csrf()
    이 코드는 '/login' url를 가진 custom login page를 생성한다. login page는 username인 'ssoId', password의 Http request parameters를 받는다.
    또한 'csrf()'의 호출을 주목한다. csrf()는 Spring Security4에서 기본 활성화되기 떄문에 선택적이다.
    그러나 만약 'CSRF protection(보호)'를 사용하지 않는 것을 원한다면, csrf().disable()을 사용해야 한다. (사용하지 않는 건 비추천)
 **/

/**
    <XML configuration>
         <http auto-config="true" >
             <intercept-url pattern="/" access="permitAll" />
             <intercept-url pattern="/home" access="permitAll" />
             <intercept-url pattern="/admin**" access="hasRole('ADMIN')" />
             <intercept-url pattern="/dba**" access="hasRole('ADMIN') and hasRole('DBA')" />
            <form-login  login-page="/login" username-parameter="ssoId" password-parameter="password" authentication-failure-url="/Access_Denied" />
            <csrf/>
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