<%--
  Created by IntelliJ IDEA.
  User: gonghojin
  Date: 2018-05-29
  Time: 오후 6:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <title>Employee 등록</title>
    <style>

      .error {
        color: #ff0000;
      }
    </style>
  </head>
  <body>
    <h2>Registration Form</h2>

    <form:form method="POST" modelAttribute="employee">
        <table>
          <tr>
            <td><label for="name">Name : </label></td>
            <td><form:input path="name" id="name" /></td>
            <td><form:errors path="name" cssClass="error" /></td>
          </tr>

          <tr>
            <td><label for="joiningDate">Joining Date : </label></td>
            <td><form:input path="joiningDate" id="joiningDate" /></td>
            <td><form:errors path="joiningDate" cssClass="error" /></td>
          </tr>

          <tr>
            <td><label for="salary">Salary: </label> </td>
            <td><form:input path="salary" id="salary"/></td>
            <td><form:errors path="salary" cssClass="error"/></td>
          </tr>

          <tr>
            <td><label for="ssn">SSN: </label> </td>
            <td><form:input path="ssn" id="ssn"/></td>
            <td><form:errors path="ssn" cssClass="error"/></td>
          </tr>

          <tr>
            <td colspan="3">
              <c:choose>
                <c:when test="${edit}">
                  <input type="submit" value="Update" />
                </c:when>
                <c:otherwise>
                  <input type="submit" value="Register" />
                </c:otherwise>
              </c:choose>
            </td>
          </tr>
        </table>
    </form:form>
    <br/>
    <br/>
    되돌아가기 <a href="<c:url value='/list' />">List of All Employees</a>
  </body>
</html>
