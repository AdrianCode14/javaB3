<%@ include file="include/importTags.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/jsp/include/importTags.jsp" />
<!DOCTYPE html>
<html>
<head>
</head>

<table border="1">
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Price</th>
    </tr>

    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.labelEn}</td>
            <td>${product.descriptionEn}</td>
            <td>${product.price}</td>
        </tr>
        <tr>
            <td colspan="3">&nbsp;</td>
        </tr>
    </c:forEach>
</table>
<footer>
    <p><fmt:message key="page.home.contact" /></p>
</footer>
</body>
</html>
