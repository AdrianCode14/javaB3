<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/importTags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="confirmOrder.title" /></title>
</head>
<body>
    <div class="container">
        <h1><spring:message code="confirmOrder.header" /></h1>
        <p><spring:message code="confirmOrder.message" /></p>
        <table class="table">
            <thead>
                <tr>
                    <th><spring:message code="cart.product" /></th>
                    <th><spring:message code="cart.price" /></th>
                    <th><spring:message code="cart.quantity" /></th>
                    <th><spring:message code="cart.total" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${cartItems}" var="entry">
                    <tr>
                        <td>${entry.value.product.getLocalizedLabel(pageContext.response.locale.language)}</td>
                        <td>${entry.value.product.price}</td>
                        <td>${entry.value.quantity}</td>
                        <td>${entry.value.product.price * entry.value.quantity}</td>
                    </tr>
                </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="3"><strong><spring:message code="confirmOrder.total" /></strong></td>
                    <td><strong>${totalPrice}</strong></td>
                </tr>
            </tfoot>
        </table>

        <c:if test="${cartItems.size() >= 2}">
            <p class="text-success"><spring:message code="confirmOrder.discount" /></p>
        </c:if>

        <form action="<c:url value='/pay'/>" method="post">
            <button type="submit" class="btn btn-primary"><spring:message code="cart.checkout" /></button>
        </form>

        <a href="<c:url value='/cart'/>" class="btn btn-secondary mt-2"><spring:message code="confirmOrder.back" /></a>
    </div>
</body>
</html>