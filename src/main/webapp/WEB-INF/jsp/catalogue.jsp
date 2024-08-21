<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/importTags.jsp"%>

<div class="container">
    <h1><spring:message code="link.catalog" /></h1>

    <form action="<c:url value='/catalogue'/>" method="get">
        <select name="category" onchange="this.form.submit()">
            <option value=""><spring:message code="category.all" /></option>
            <c:forEach var="category" items="${categories}">
                <option value="${category.labelEn}" ${category.labelEn == selectedCategory ? 'selected' : ''}>
                        ${category.getName(locale)}
                </option>
            </c:forEach>
        </select>
    </form>

    <div class="product-list">
        <c:forEach var="product" items="${products}">
            <div class="product-item">
                <h2><a href="<c:url value='/catalogue/product/${product.productId}'/>">${product.getLocalizedLabel(locale.language)}</a></h2>
                <p>${product.getLocalizedDescription(locale.language)}</p>
                <p class="price"><spring:message code="cart.price" />: ${product.price}</p>
                <form action="<c:url value='/cart/add'/>" method="post">
                    <input type="hidden" name="productId" value="${product.productId}" />
                    <input type="number" name="quantity" value="1" min="1" />
                    <button type="submit"><spring:message code="cart.placeOrder" /></button>
                </form>
            </div>
        </c:forEach>
    </div>
</div>