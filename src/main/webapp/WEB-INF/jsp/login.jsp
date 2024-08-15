<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="include/importTags.jsp" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div class="login-container">
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger" role="alert">
                ${errorMessage}
        </div>
    </c:if>
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success" role="alert">
                ${successMessage}
        </div>
    </c:if>

    <form:form method="post" modelAttribute="user">
        <div>
            <form:label path="email">Email:</form:label>
            <form:input path="email" type="email" required="true"/>
        </div>
        <div>
            <form:label path="password">Password:</form:label>
            <form:password path="password" required="true"/>
        </div>
        <form:button>Login</form:button>
    </form:form>

    <a href="<spring:url value='/register'/>">Not registered yet? Click here!</a>
</div>
</body>
</html>