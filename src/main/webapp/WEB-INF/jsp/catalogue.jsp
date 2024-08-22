<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/importTags.jsp"%>

<div class="container mt-4">
    <h1 class="mb-4"><spring:message code="link.catalog" /></h1>

    <form action="<c:url value='/catalogue'/>" method="get" class="mb-4">
        <select name="category" onchange="this.form.submit()" class="form-select">
    <option value="" <c:if test="${empty selectedCategory}">selected</c:if>>
        <spring:message code="category.all" />
    </option>
    <c:forEach var="category" items="${categories}">
        <option value="${category.labelEn}" ${category.labelEn == selectedCategory ? 'selected' : ''}>
            ${category.getName(locale)}
        </option>
    </c:forEach>
</select>
    </form>

    <div class="row">
        <c:forEach var="product" items="${products}">
            <div class="col-md-4 mb-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${product.getLocalizedLabel(locale.language)}</h5>
                        <p class="card-text">${product.getLocalizedDescription(locale.language)}</p>
                        <p class="card-text"><strong><spring:message code="cart.price" />: ${product.price}</strong></p>
                        <form action="<c:url value='/cart/add'/>" method="post">
                            <input type="hidden" name="productId" value="${product.productId}" />
                            <div class="input-group mb-3">
                                <input type="number" name="quantity" value="1" min="1" class="form-control" />
                                <button type="submit" class="btn btn-primary"><spring:message code="cart.placeOrder" /></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>