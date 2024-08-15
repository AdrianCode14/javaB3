<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/importTags.jsp"%>

<div class="container">
    <h1><spring:message code="page.home.title" /></h1>

    <div class="welcome-message">
        <h2><spring:message code="page.home.welcome" /></h2>
        <p><spring:message code="page.home.monthly.books" /></p>
        <ul>
            <li><spring:message code="page.home.book1.title" /></li>
            <li><spring:message code="page.home.book2.title" /></li>
            <li><spring:message code="page.home.book3.title" /></li>
        </ul>
    </div>

    <div class="catalog-link">
        <p>
            <spring:message code="page.home.start.catalog" />
            <a href="<spring:url value='/catalogue'/>"><spring:message code="page.home.catalog.link" /></a>.
        </p>
    </div>

    <div class="contact-info">
        <p><spring:message code="page.home.contact" /></p>
    </div>
</div>