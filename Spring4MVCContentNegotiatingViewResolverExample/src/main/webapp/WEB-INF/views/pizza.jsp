<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pizza JSP View</title>
</head>
<body>
	<table border = "1">
		 <tr>
        <td>NAME</td>
        <td>Flavor</td>
        <td>Toppings</td>
        </tr>
        <tr>
            <td>${pizza.name}</td>
            <td>${pizza.flavor}</td>
			<td>
				<c:forEach var = "item" items = "${pizza.toppings }">
					<c:out value = "${item }" />&nbsp;
				</c:forEach>
			</td>
	</table>
</body>
</html>