<%--
  Created by IntelliJ IDEA.
  User: gonghojin
  Date: 2018-05-31
  Time: 오후 1:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>allEmployes Form</title>
    <style>
        tr:first_child {
            font-weight: bold;
            background-color: #C6D904;
        }
    </style>
</head>
<body>
<h2>List of Employees</h2>
<table>
    <tr>
        <td>NAME</td>
        <td>Joining Date</td>
        <td>Salary</td>
        <td>SSN</td>
        <td></td>
    </tr>
    <c:forEach items="${employees}" var="employee">
        <tr>
            <td>${employee.name}</td>
            <td>${employee.joiningDate}</td>
            <td>${employee.salary}</td>
            <td><a href="<c:url value='/edit-${employee.ssn}-employee' />">${employee.ssn}</a></td>
            <td><a href="<c:url value='/delete-${employee.ssn}-employee' />">delete</a></td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="<c:url value='/new' />">Add New Employee</a>
</body>
</html>
