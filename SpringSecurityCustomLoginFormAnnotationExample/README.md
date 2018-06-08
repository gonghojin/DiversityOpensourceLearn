Spring Security 4 Custom Login Form Annotation+XML Example
==========================================================
이 글은 Spring Security 4에서 **custom login form**을 생성하고 SpringMVC web application에 통합하는 방법을 보여준다.
Spring Security 4 Hello World Annotation + xml example 예제에서, 우리가 form을 구체하지 않을 경우에  Spring Security에서 제공되는 기본적인 logic form을 봤었다.
기본적으로, Security Configuration에서는 formLogin()과 함께 **loginPage(URL)** function에 대한 호출을 첨부한다 .(예) - .and().formLogin().loginPage("/logion")<br>
그후, 이 **'/login** URL을 우리에 의해서 정의된 login view를 리턴하는 Spring MVC Contrlloer에 매핑한다.<br>
로그인을 시도할 떄, 특정된 logion view가 표시된다. login 기능의 나머지는 동일하게 유지된다.<br>

Following technologies being used:<br>
•Spring 4.1.6.RELEASE<br>
•Spring Security 4.0.1.RELEASE<br>
•Maven 3<br>
•JDK 1.7<br>
•Tomcat 8.0.21<br>
•Eclipse JUNO Service Release 2<br>


원문 : http://websystique.com/spring-security/spring-security-4-custom-login-form-annotation-example/
