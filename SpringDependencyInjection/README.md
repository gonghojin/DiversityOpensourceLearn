Spring Dependency Injection Example with Constructor and Property Setter (XML)
==============================================================================
Spring의 주된 목적은 가능한한 component의 독립성을 유지하는 거다.
이점은 components들이 재사용될 수 있게 해주며, 또한 unit test를 독립적으로 할 수 있게 해준다.
Spring Dependency injection은 dependency(작업을 완료하기 위해서 어떠한 component가 필요하기 떄문에, component 스스로가 관련 dependency를 찾음)를 inject(주입)하면서 이러한 목적을 이행한다.

이 포스트에서는 XML 기반 환경설정에 초점을 맞출 뿐만 아니라 Annotation의 예도 살필 것이다.
<br>

Spring은 dependency inject 위해서 주로 2 가지 방법을 제공한다
+ Poperty Setter
+ Constructor injection
<br>

원문 : http://websystique.com/spring/spring-dependency-injection-example-with-constructor-and-property-setter-xml-example/
------------------------------------------------------------------------------------------------