<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/importTags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="orderConfirmation.title" /></title>
</head>
<body>
    <div class="container">
        <h1><spring:message code="orderConfirmation.header" /></h1>
        <p><spring:message code="orderConfirmation.message" /></p>

        <div class="order-details">
            <h2><spring:message code="orderConfirmation.orderDetails" /></h2>
            <p><strong><spring:message code="orderConfirmation.orderId" />:</strong> ${order.orderId}</p>
            <p><strong><spring:message code="orderConfirmation.date" />:</strong> <fmt:formatDate value="${order.date}" pattern="dd-MM-yyyy HH:mm" /></p>
            <p><strong><spring:message code="orderConfirmation.status" />:</strong> ${order.orderStatus}</p>
            <p><strong><spring:message code="orderConfirmation.total" />:</strong> <fmt:formatNumber value="${order.totalPrice}" type="currency" currencySymbol="€" /></p>
        </div>

        <a href="<c:url value='/' />" class="btn btn-primary"><spring:message code="orderConfirmation.backToHome" /></a>
    </div>
</body>
</html>