
<jsp:include page="/WEB-INF/jsp/include/importTags.jsp" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="page.description.title" /></title>
</head>
<body>
<h1><fmt:message key="page.description.welcome" /></h1>
<p>
    <fmt:message key="page.description.intro" />
</p>

<h2><fmt:message key="page.description.services" /></h2>
<ul>
    <li><fmt:message key="page.description.service1" /></li>
    <li><fmt:message key="page.description.service2" /></li>
    <li><fmt:message key="page.description.service3" /></li>
    <li><fmt:message key="page.description.service4" /></li>
    <li><fmt:message key="page.description.service5" /></li>
</ul>

<h2><fmt:message key="page.description.location" /></h2>
<p>
    <fmt:message key="page.description.location.info" />
</p>

<h2><fmt:message key="page.description.contact" /></h2>
<p>
    <fmt:message key="page.description.contact.address" /><br>
    <fmt:message key="page.description.contact.phone" /><br>
    <fmt:message key="page.description.contact.email" />
</p>

<footer>
    &copy; 2024 OurBookshop
</footer>
</body>
</html>
