<%--
  Created by IntelliJ IDEA.
  User: gonghojin
  Date: 2018-06-01
  Time: 오후 5:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>DBA Page</title>
</head>
<body>
    Dear <string>${user}</string>, Welcome to DBA page.
    <a href="<c:url value='/logout' />">Logout</a>
</body>
</html>
