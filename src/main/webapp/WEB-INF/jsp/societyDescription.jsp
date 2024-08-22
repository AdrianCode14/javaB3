<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/importTags.jsp"%>

<div class="container py-5">
    <h1 class="mb-4 text-center"><spring:message code="society.title"/></h1>

    <div class="row align-items-center mb-5">
        <div class="col-lg-8">
            <p class="lead"><spring:message code="society.description"/></p>
        </div>
        <div class="col-lg-4">
            <img src="<c:url value='/images/bookstore.png'/>" alt="Our Bookstore" class="img-fluid rounded shadow-sm">
        </div>
    </div>

    <div class="row mb-5">
        <div class="col-md-6">
            <div class="card h-100">
                <div class="card-body">
                    <h2 class="card-title"><spring:message code="society.mission.title"/></h2>
                    <p class="card-text"><spring:message code="society.mission.description"/></p>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card h-100">
                <div class="card-body">
                    <h2 class="card-title"><spring:message code="society.values.title"/></h2>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item"><spring:message code="society.values.item1"/></li>
                        <li class="list-group-item"><spring:message code="society.values.item2"/></li>
                        <li class="list-group-item"><spring:message code="society.values.item3"/></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="bg-light p-4 rounded">
        <h2 class="mb-3"><spring:message code="society.contact.title"/></h2>
        <address>
            <strong>BookShop</strong><br>
            123 Livre Street<br>
            75000 Paris, France<br>
            <abbr title="Phone">P:</abbr> +33 1 23 45 67 89
        </address>
    </div>
</div>