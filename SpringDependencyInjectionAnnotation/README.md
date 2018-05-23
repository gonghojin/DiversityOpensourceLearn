Spring Dependency Injection Annotation Example, Beans Auto-wiring using @Autowired, @Qualifier & @Resource Annotations Configuration
=====================================================================================================================================
이 포스트는 Spring Dependency Injection Annotation + Spring Auto-wiring Annotation 예제이다.
우리는 Spring Dependency Injection과 Spring **@Autowired** annoation을 사용하는 Beans auto-wiring 배울 것이다.
**@Autowired**는 bean의 생성자, field, setter method 그리고 Spring's dependency injection을 사용하는 dependency를 autowire 하기 위한 config method 위에 적용된다.
<br>

@Autowired는 **bean datatype**을 사용하는 dependency를 wire한다. 만약 bean name(XML의 byBame과 다소 유사)을 사용하는 bean wiring을 찾는다면, 'name' 속성과 함께 표준 **@Resource** annotation을 사용할 수 있다.<br>
**@Qualifier** annotation은 injected type의 bean이 하나 또는 그 이상이 application context에 존재할 경우, 애매모호함을 해결하기 위해서 @Autowired와 함께 자주 사용되어진다. 시작해 보자!!

원문 : http://websystique.com/spring/spring-dependency-injection-annotation-beans-auto-wiring-using-autowired-qualifier-resource-annotations-configuration/
-----------------------------------------------------------------------------------------------------------------------------------------------------------