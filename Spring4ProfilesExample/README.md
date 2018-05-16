Spring @Profile Guide
=====================
이 포스트에서는 Spring **@Profile** annotation을 분석하며, 다른 environments에서 조건적으로 사용할 수 있는 다른 형태의 beans or configuration을 만들기 위해서 사용한다.

database-interaction(상호작용)을 포함하는 application이 있다고 가정하자.<br>
개발환경을 위해서 하나의 dataSource(예 Mysql)를 환경설정하기 원하고 반면에 Product을 위해서 완전히 다른 dataSource(예 Oracle)를 원한다.<br>
**Spring Profiles**를 사용해서 그러한 구성을 관리할 수 있다.<br>

+ Spring 4.0.6.RELEASE<br>
+ Maven 3<br>
+ JDK 1.6<br>
+ Eclipse JUNO Service Release 2<br>