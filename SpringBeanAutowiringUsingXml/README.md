Spring Beans Auto-wiring Example using XML Configuration
========================================================
Bean wiring은 bean이 작업을 완료하기 위해서 필요할 수 있는 dependencies를 제공하는 것과 부합한다.
스프링에서, beans들은 두 가지 방법으로 의해서 wired되어 질 수 있다. : Manually(수동) 또는 Autowiring

####Manual wiring : property 또는 constructor 태그 안에 **ref** 속성을 사용한다.

이 접근법에서는, wired 되어지길 원하는  bean을 참조하기 위해서 'ref' 속성을 사용한다.
이 방법이 가장 깔끔한 접근법이며, 이해하기 가장 쉽다.<br>
>기본 예제 (autowired=no)<br>
\<bean id="driver" class="com.gongdel.spring.domain.Driver"><br>
         \<property name="license" ref="license" /><br>
\</bean><br>


####Autowiring : \<bean> 태그 안에 **autowired** 속성 사용
>\<bean id="application" class="com.gongdel.spring.domain.Application" autowire="byName" /><br>

이 접근법에서는, Spring autowire 특성을 사용하여 beans는 자동적으로 wired 되어진다.
autowiring을 위해서 4 가지 지원되는 options이 있다.<br>
+ **autowired="byName"** : Autowring은 property name을 사용한다. 만약 같은 이름을 가진 다른 bean의 속성이 찾아질 경우, 이 bean은 다른 beans의 속성에 연결된다.
+ **autowired="byType"** : 만약 같은 타입을 가진 다른 bean의 속성이 찾아질 경우, 이 bean은 다른 beans의 속성을 연결한다.
+ **autowired="constructor"** : 만약 같은 타입을 가진 다른 bean의 생성자가 찾아질 경우, 이 bean은 다른 bean 생성자를 연결한다.
+ **autowired="no"** : No Autowiring. 'ref' 속성을 사용하여 분명하게 명시하는 bean과 같음<br>

각각의 autowring을 예제로 이해 해보자.



원문 : http://websystique.com/spring/spring-beans-auto-wiring-example-using-xml-configuration/
-----------------------------------------------------------------------------------------------
 