Spring @PropertySource & @Value annotations example
====================================================
이 포스트에서는 **@PropertySource and @Value**annotation을 사용하여 properties file로부터 값을 어떻게 읽는지 볼 것이다.
또한 Spring **Environment** interface 사용에 대해서 설명할 것이다.

Spring **@PropertySource**s는 조로 Spring's Environment interface를 사용하여 properties file을 읽을 때 사용된다.
이 annotation은 **@Configuration** classes에 위치한다.<br>
Spring @Value annoation은 필드 또는 메소드 위에 구체적인 표현을 위해 사용되어 진다.
<br>

  • Spring 4.0.6.RELEASE<br>
  • Maven 3<br>
  • JDK 1.6<br>
  • Eclipse JUNO Service Release 2
