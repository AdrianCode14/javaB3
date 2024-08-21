<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/importTags.jsp"%>

<div class="container">
    <h1 class="my-4"><spring:message code="society.title"/></h1>

    <div class="row">
        <div class="col-md-8">
            <p><spring:message code="society.description"/></p>
        </div>
        <div class="col-md-4">
            <img src="<c:url value='/images/bookstore.png'/>" alt="Our Bookstore" class="img-fluid rounded mb-3">
        </div>
    </div>

    <h2 class="mt-4"><spring:message code="society.mission.title"/></h2>
    <p><spring:message code="society.mission.description"/></p>

    <h2 class="mt-4"><spring:message code="society.values.title"/></h2>
    <ul>
        <li><spring:message code="society.values.item1"/></li>
        <li><spring:message code="society.values.item2"/></li>
        <li><spring:message code="society.values.item3"/></li>
    </ul>

    <h2 class="mt-4"><spring:message code="society.contact.title"/></h2>
    <address>
        <strong>BookShop</strong><br>
        123 Livre Street<br>
        75000 Paris, France<br>
        <abbr title="Phone">P:</abbr> +33 1 23 45 67 89
    </address>
</div>