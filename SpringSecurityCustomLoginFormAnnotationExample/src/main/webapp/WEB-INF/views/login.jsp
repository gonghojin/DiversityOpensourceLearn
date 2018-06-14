<%--
  Created by IntelliJ IDEA.
  User: gonghojin
  Date: 2018-06-08
  Time: 오후 3:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
    <link rel="stylesheet" type="text/css"
          href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css"/>
</head>
<body>
<div id="mainWrapper">
    <div class="login-container">
        <div class="login-form">
            <c:url var="loginUrl" value="/login"/>
            <form action="${loginUrl}" method="post" class="form-horizontal">
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger">
                        <p>Invalid username and password.</p>
                    </div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="alert alert-success">
                        <p>You have been logged out successfully.</p>
                    </div>
                </c:if>
                <div class="input-group input-sm">
                    <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                    <input type="text" class="form-control" id="username" name="ssoId" placeholder="Enter Username"
                           required>
                </div>
                <div class="input-group input-sm">
                    <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
                    <input type="password" class="form-control" id="password" name="password"
                           placeholder="Enter Password" required>
                </div>
                <div class="input-group input-sm">
                    <div class="checkbox">
                        <label><input type="checkbox" id="rememberme" name="remember-me"> Remember Me</label>
                    </div>
                </div>
                <!-- CSRF attacks으로 보호하기 위해서 필수적인 부분-->
                <!-- 보다시피 CSRF parameters는 JSP에서 EL Expressions를 사용하여 접근한다. -->
                <!-- 추가적으로, jsp 맨위에 EL expressions를 강요하는 것이 더 낫다. Sevlet 2.4 이상은 기본적으로 포함되지만, 미만은 포함이 안되기 떄문에 EL 표현 인식 못함 -->
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="form-actions">
                    <input type="submit"
                           class="btn btn-block btn-primary btn-default" value="Log in">
                </div>
            </form>

        </div>
    </div>
</div>
</body>
</html>
