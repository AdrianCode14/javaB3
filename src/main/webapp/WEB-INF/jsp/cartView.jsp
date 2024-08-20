<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/importTags.jsp"%>

<div class="container">
    <h1><spring:message code="cart.title" /></h1>

    <c:if test="${empty cart.items}">
        <p><spring:message code="cart.empty" /></p>
    </c:if>

    <c:if test="${not empty cart.items}">
        <table class="table">
            <thead>
            <tr>
                <th><spring:message code="cart.product" /></th>
                <th><spring:message code="cart.price" /></th>
                <th><spring:message code="cart.quantity" /></th>
                <th><spring:message code="cart.total" /></th>
                <th><spring:message code="cart.actions" /></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="entry" items="${cart.items}">
                <tr>
                    <td>${entry.value.product.getLocalizedLabel(pageContext.response.locale.language)}</td>
                    <td>${entry.value.product.price}</td>
                    <td>
                        <form action="<c:url value='/cart/update'/>" method="post">
                            <input type="hidden" name="productId" value="${entry.value.product.productId}" />
                            <input type="number" name="quantity" value="${entry.value.quantity}" min="1" onchange="this.form.submit()" />
                        </form>
                    </td>
                    <td>${entry.value.product.price * entry.value.quantity}</td>
                    <td>
                        <form action="<c:url value='/cart/remove'/>" method="post">
                            <input type="hidden" name="productId" value="${entry.value.product.productId}" />
                            <button type="submit" class="btn btn-danger"><spring:message code="cart.remove" /></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="3"><strong><spring:message code="cart.total" /></strong></td>
                <td><strong>${totalWithDiscount}</strong></td>
                <td></td>
            </tr>
            </tfoot>
        </table>

        <!-- Formulaire pour initier le paiement -->
        <form action="<c:url value='/pay'/>" method="post">
            <button type="submit" class="btn btn-primary"><spring:message code="cart.checkout" /></button>
        </form>
    </c:if>

    <a href="<c:url value='/catalogue'/>" class="btn btn-secondary"><spring:message code="cart.continueShopping" /></a>
</div>
