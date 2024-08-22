<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/importTags.jsp"%>

<div class="container mt-4">
    <div class="row">
        <div class="col-md-8">
            <h1>${product.getLocalizedLabel(locale.language)}</h1>
            <p>
                <c:choose>
                    <c:when test="${locale.language == 'fr'}">
                        ${product.descriptionFr}
                    </c:when>
                    <c:otherwise>
                        ${product.descriptionEn}
                    </c:otherwise>
                </c:choose>
            </p>
            <p><strong><spring:message code="cart.price" />: ${product.price}</strong></p>

            <form action="<c:url value='/cart/add'/>" method="post">
                <input type="hidden" name="productId" value="${product.productId}" />
                <div class="form-group">
                    <label for="quantity"><spring:message code="cart.quantity" /></label>
                    <input type="number" name="quantity" value="1" min="1" class="form-control" style="max-width: 100px;" />
                </div>
                <button type="submit" class="btn btn-primary mt-2"><spring:message code="cart.placeOrder" /></button>
            </form>
        </div>
        <div class="col-md-4">
            <h3><spring:message code="product.similarProducts" /></h3>
            <ul class="list-group">
                <c:forEach var="similarProduct" items="${similarProducts}" end="4">
                    <li class="list-group-item">
                        <a href="<c:url value='/product/${similarProduct.labelEn}-${similarProduct.productId}'/>">${similarProduct.getLocalizedLabel(locale.language)}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <a href="<c:url value='/catalogue'/>" class="btn btn-secondary mt-3"><spring:message code="cart.continueShopping" /></a>
</div>