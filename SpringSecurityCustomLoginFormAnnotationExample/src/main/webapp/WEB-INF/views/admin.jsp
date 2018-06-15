<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: gonghojin
  Date: 2018-06-08
  Time: 오후 3:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DBA page</title>
</head>
<body>
    Dear <strong>${user}</strong>, Welcome to DBA Page.
    <sec:authorize access="isFullyAuthenticated()">
        <label><a href="#">Create New User</a> | <a href="#">View existing Users</a></label>
    </sec:authorize>
    <sec:authorize access="isRememberMe()">
        <label><a href="#">View existing Users</a></label>
    </sec:authorize>
    <a href="<c:url value="/logout" />">Logout</a>
</body>
</html>
