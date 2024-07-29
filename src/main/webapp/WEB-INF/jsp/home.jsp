<%@ include file="include/importTags.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/jsp/include/importTags.jsp" />
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="page.home.title" /></title>
</head>
<body>
<h1><fmt:message key="page.home.welcome" /></h1>

<p><fmt:message key="page.home.monthly.books" /></p>
<ul>
    <p>Le meilleur site de vente en ligne </p>

</ul>
<p><fmt:message key="page.home.start.catalog" /><a><fmt:message key="page.home.catalog.link" /></a>.</p>

<footer>
    <!-- Ajoutez ici le contenu du pied de page -->
    <p><fmt:message key="page.home.contact" /></p>
</footer>
</body>
</html>
