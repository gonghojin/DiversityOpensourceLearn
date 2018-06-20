<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gonghojin
  Date: 2018-06-14
  Time: 오후 3:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Registration Form</title>
    <link href="<c:url value='/static/css/bootstrap.css'/>" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
<div class="success">
    Confirmation message : ${success}
    <br>
    Would you like to <a href="<c:url value='/newUser' />">Add More Users</a>?
    <br>
    Go to <a href="<c:url value='/admin' />">Admin Page</a> OR <a href="<c:url value="/logout" />">Logout</a>
    <br>
    <span class="well pull-left">
            <a href="<c:url value='/add-document-${user.id}' />">Click here to upload/manage your documents</a>
    </span>
</div>

</body>