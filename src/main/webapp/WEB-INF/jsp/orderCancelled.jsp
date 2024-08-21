<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/importTags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="orderCancelled.title" /></title>
</head>
<body>
    <div class="container">
        <h1><spring:message code="orderCancelled.header" /></h1>
        <p><spring:message code="orderCancelled.message" /></p>

        <div class="order-details">
            <h2><spring:message code="orderCancelled.orderDetails" /></h2>
            <p><strong><spring:message code="orderCancelled.orderId" />:</strong> ${order.orderId}</p>
            <p><strong><spring:message code="orderCancelled.date" />:</strong> <fmt:formatDate value="${order.date}" pattern="dd-MM-yyyy HH:mm" /></p>
            <p><strong><spring:message code="orderCancelled.status" />:</strong> ${order.orderStatus}</p>
        </div>

        <a href="<c:url value='/cart' />" class="btn btn-primary"><spring:message code="orderCancelled.backToCart" /></a>
        <a href="<c:url value='/home' />" class="btn btn-secondary"><spring:message code="orderCancelled.backToHome" /></a>
    </div>
</body>
</html>