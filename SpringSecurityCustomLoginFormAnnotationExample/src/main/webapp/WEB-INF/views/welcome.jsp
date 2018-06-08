<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: gonghojin
  Date: 2018-06-08
  Time: 오후 3:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WelcomPage</title>
</head>
<body>

    Greeting : ${greeting}
    This is a welcome page.

    Dear <strong>${user}</strong>, Welcome to Home Page.
    <a href="<c:url value="/logout" />">Logout</a>

    <br/>
    <br/>
    <div>
        <label>누구에게나 보이는 부분</label>
    </div>

    <br/>
    <div>
        <sec:authorize access="hasRole('ADMIN')">
            <labe>ADMIN 권한자에게만 보이는 부분</labe>
        </sec:authorize>
    </div>
    <br/>
    <div>
        <sec:authorize access="hasRole('ADMIN') and hasRole('DBA')">
            <labe>ADMIN과 DBA에게 보이는 부분</labe>
        </sec:authorize>
    </div>
</body>

</html>
