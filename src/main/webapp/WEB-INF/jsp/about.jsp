<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/importTags.jsp"%>

<div class="container">
    <h1><spring:message code="page.description.title" /></h1>

    <div class="description-content">
        <h2><spring:message code="page.description.welcome" /></h2>
        <p><spring:message code="page.description.intro" /></p>

        <h3><spring:message code="page.description.services" /></h3>
        <ul>
            <li><spring:message code="page.description.service1" /></li>
            <li><spring:message code="page.description.service2" /></li>
            <li><spring:message code="page.description.service3" /></li>
            <li><spring:message code="page.description.service4" /></li>
            <li><spring:message code="page.description.service5" /></li>
        </ul>

        <h3><spring:message code="page.description.location" /></h3>
        <p><spring:message code="page.description.location.info" /></p>

        <h3><spring:message code="page.description.contact" /></h3>
        <p><spring:message code="page.description.contact.address" /></p>
        <p><spring:message code="page.description.contact.phone" /></p>
        <p><spring:message code="page.description.contact.email" /></p>
    </div>
</div>