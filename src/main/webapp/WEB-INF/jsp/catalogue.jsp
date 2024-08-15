<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/importTags.jsp"%>

<div class="container">
    <h1><spring:message code="link.catalog" /></h1>

    <div class="product-list">
        <c:forEach var="product" items="${products}">
            <div class="product-item">
                <h2>${product.localizedLabel(pageContext.response.locale.language)}</h2>
                <p>${product.localizedDescription(pageContext.response.locale.language)}</p>
                <p class="price"><spring:message code="cart.price" />: ${product.price}</p>
                <form action="<spring:url value='/cart/add'/>" method="post">
                    <input type="hidden" name="productId" value="${product.productId}" />
                    <input type="number" name="quantity" value="1" min="1" />
                    <button type="submit"><spring:message code="cart.placeOrder" /></button>
                </form>
            </div>
        </c:forEach>
    </div>
</div>
