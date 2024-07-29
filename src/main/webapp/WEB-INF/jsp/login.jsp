<%@ page pageEncoding="UTF-8"
         contentType="text/html; charset=UTF-8" %>
<%@ include file="include/importTags.jsp" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success d-flex align-text-center" role="alert">
            ${successMessage}
        </div>
    </c:if>
    <div class="login-container">
        <form:form method="post" modelAttribute="user">
            <form:label path="email">
            </form:label>
            <form:input path="email"/>
            <br/>
            <br/>
            <form:label path="password">
            </form:label>
            <form:input path="password" type="password"/>
            <form:button>Submit</form:button>
        </form:form>
        <c:if test="${not empty errorMessage}">
            <p class="error">${errorMessage}</p>
        </c:if>
        <a href="<spring:url value="/register"/>"> Pas encore inscrit? Cliquez-ici!</a>
    </div>
</body>
</html>