Spring Security 4 Custom Login Form Annotation+XML Example
==========================================================
이 글은 Spring Security 4에서 **custom login form**을 생성하고 SpringMVC web application에 통합하는 방법을 보여준다.
Spring Security 4 Hello World Annotation + xml example 예제에서, 우리가 form을 구체하지 않을 경우에  Spring Security에서 제공되는 기본적인 logic form을 봤었다.
기본적으로, Security Configuration에서는 formLogin()과 함께 **loginPage(URL)** function에 대한 호출을 첨부한다 .(예) - .and().formLogin().loginPage("/logion")<br>
그후, 이 **'/login** URL을 우리에 의해서 정의된 login view를 리턴하는 Spring MVC Contrlloer에 매핑한다.<br>
로그인을 시도할 떄, 특정된 logion view가 표시된다. login 기능의 나머지는 동일하게 유지된다.<br>

<1회차><br>
Following technologies being used:<br>
•Spring 4.1.6.RELEASE<br>
•Spring Security 4.0.1.RELEASE<br>
•Maven 3<br>
•JDK 1.7<br>
•Tomcat 8.0.21<br>
•Eclipse JUNO Service Release 2<br>

원문 : <br>
기본 시큐리티:  http://websystique.com/spring-security/spring-security-4-custom-login-form-annotation-example/
    
<2회차><br>
•Hibernate 4.3.6.Final<br>
•MySQL Server 5.6<br>
원문 :<br>
Hibernate : http://websystique.com/spring-security/spring-security-4-hibernate-annotation-example/<br>

<3회차><br>
3회차에서는 Hibernate 사용하는 SpringSecurity4에서 권한 기반 login 사용을 어떻게 하는 지 본다.
이는 user가 그들의 관련된 권한에 따라서 다른 URL에 redirect 되는 것을 의미한다.
<br>
3회차에서는 2회차에서 했던 Hibernate 기능 추가를 완성하며, 권한 기반 login 기능을 추가한다.(몇 가지를 제외하고 2회차와 99% 비슷)

원문 : <br>
권한 기반 로그인 - http://websystique.com/spring-security/spring-security-4-hibernate-role-based-login-example/<br>

<4회차><br> - Spring Security 4 Hibernate Password Encoder Bcrypt Example<br>
4회차에서는 **BCryptPasswordEncoder**를 사용하여 Password Encoding을 한다.<br>
보안을 중요 시 하는 application은 **절대** 암호를 일반 text format으로 저장하면 안된다. 암호는 항상 안전한 hashing algorithm을 사용하여 encoded 되어야 한다.<br>
적절한 SALT와 결합하면 암호를 encoding 하는 데 좋은 선택이 될 수 있는 SHA 또는 MD5와 같은 많은 표준 알고리즘이 있다.<br>
Spring Security는 **BCryptPasswordEncoder**를 제공한다. <br>
BCryptPasswordEncoder : 암호를 encode 하기 위한 **강한 해시 함수 BCrypt**을 사용하는 **PasswordEncoder** interface 구현체<br>
<br>
application에서 어디에서 Password Encoding이 필요할까?<br>
1. 암호를 비교하는 동안, 인코딩되어 database에 저장된 값과 비교하기 전에 입력 암호를 Encode 한다.
2. 새로운 유저를 생성하거나 존재하는 유저의 암호를 업데이트하는 동안, database에 저장하거나 업데이트하기 전에 입력 암호를 Encode한다.<br>

원문 : <br>
암호 인코딩 - http://websystique.com/spring-security/spring-security-4-password-encoder-bcrypt-example-with-hibernate/

