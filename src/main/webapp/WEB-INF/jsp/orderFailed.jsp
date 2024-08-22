<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/importTags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="orderFailed.title" /></title>
</head>
<body>
    <div class="container">
        <h1><spring:message code="orderFailed.header" /></h1>
        <p><spring:message code="orderFailed.message" /></p>

        <div class="order-details">
            <h2><spring:message code="orderFailed.orderDetails" /></h2>
            <p><strong><spring:message code="orderFailed.orderId" />:</strong> ${order.orderId}</p>
            <p><strong><spring:message code="orderFailed.date" />:</strong> <fmt:formatDate value="${order.date}" pattern="dd-MM-yyyy HH:mm" /></p>
            <p><strong><spring:message code="orderFailed.status" />:</strong> ${order.orderStatus}</p>
        </div>

        <a href="<c:url value='/cart' />" class="btn btn-primary"><spring:message code="orderFailed.backToCart" /></a>
        <a href="<c:url value='/' />" class="btn btn-secondary"><spring:message code="orderFailed.backToHome" /></a>
    </div>
</body>
</html>