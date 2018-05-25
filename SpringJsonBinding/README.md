Converting JSON to/from Java Objects using JACKSON API
======================================================
이 글은 Java 프로젝트에서 Jackson API를 사용하여 JSON을 처리할 때, 일반적인 접근법인 **Jackson data-binding**을 설명한다.<br>
***
**ObjectMapper**는 data-binding을 위해서 사용하는 main api이다. from/to Java and JSON으로 전환하기 위한 몇 가지 reader/writer method가 함께 제공된다.<br>
다음은 일반적인 사용법이다.
+ JSON을 읽고 java objects로 변환<br>
    + ObjectMapper mapper = new ObjectMapper();<br>
      Object value = mapper.readValue(JSON-SOURCE, DESTINATION-JAVA-OBJECT-TYPE); // JSON-SOURCE는 File/InputStream/String 등이 될 수 있음<br>
+ java objects를 JSON으로 쓰기<br>
    + ObjectMapper mapper = new ObjectMapper();<br>
      Object value = mapper.writeValue(JSON-DESTINATION, SOURCE-JAVA-OBJECT); // JSON-DESTINATION은 File/InputStream/String 등이 될 수 있음<br>
      

원문 : http://websystique.com/java/json/jackson-convert-java-object-to-from-json/
                                     