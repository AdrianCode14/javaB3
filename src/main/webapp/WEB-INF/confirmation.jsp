<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/importTags.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><spring:message code="cart.confirmation.title" /></title>
</head>
<body>
<div class="container">
    <h1><spring:message code="cart.confirmation.title" /></h1>

    <p><spring:message code="cart.confirmation.message" /></p>

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
        <c:forEach var="entry" items="${cart.items}">
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
            <td colspan="3"><strong><spring:message code="cart.total" /></strong></td>
            <td><strong>${cartService.calculateTotalWithDiscount(cart)}</strong></td>
        </tr>
        </tfoot>
    </table>

    <!-- Formulaire pour confirmer la commande -->
    <form action="<c:url value='/cart/placeOrder'/>" method="post">
        <button type="submit" class="btn btn-success"><spring:message code="cart.confirmOrder" /></button>
    </form>

    <!-- Formulaire pour annuler la commande -->
    <form action="<c:url value='/cart'/>" method="post">
        <button type="submit" class="btn btn-secondary"><spring:message code="cart.cancelOrder" /></button>
    </form>
</div>
</body>
</html>
