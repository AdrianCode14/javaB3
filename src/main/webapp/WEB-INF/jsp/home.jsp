<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="include/importTags.jsp"%>

<div class="container mt-5">
    <div class="jumbotron bg-light p-5 rounded">
        <h1 class="display-4"><spring:message code="page.home.welcome" /></h1>
        <p class="lead"><spring:message code="page.home.intro" /></p>
        <hr class="my-4">
        <p>
            <spring:message code="page.home.start.catalog" />
            <a href="<spring:url value='/catalogue'/>" class="btn-sm btn-primary">
                <spring:message code="page.home.catalog.link" />
            </a>
        </p>
            </div>
    <section class="mt-5">
        <h2 class="text-center mb-4"><spring:message code="page.home.monthly.books" /></h2>
        <div class="row">
            <div class="col-md-4 mb-4">
                <div class="card h-100 shadow-sm">
                    <img src="<c:url value='/images/book1.jpg'/>" class="card-img-top" alt="Book 1" onerror="this.src='<c:url value='/images/book1.jpg'/>';">
                    <div class="card-body">
                        <h5 class="card-title"><spring:message code="page.home.book1.title" /></h5>
                        <p class="card-text"><spring:message code="page.home.book1.description" /></p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="card h-100 shadow-sm">
                    <img src="<c:url value='/images/book2.jpg'/>" class="card-img-top" alt="Book 2" onerror="this.src='<c:url value='/images/book2.jpg'/>';">
                    <div class="card-body">
                        <h5 class="card-title"><spring:message code="page.home.book2.title" /></h5>
                        <p class="card-text"><spring:message code="page.home.book2.description" /></p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="card h-100 shadow-sm">
                    <img src="<c:url value='/images/book3.jpg'/>" class="card-img-top" alt="Book 3" onerror="this.src='<c:url value='/images/book3.jpg'/>';">
                    <div class="card-body">
                        <h5 class="card-title"><spring:message code="page.home.book3.title" /></h5>
                        <p class="card-text"><spring:message code="page.home.book3.description" /></p>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>

<style>
    .jumbotron {
        background-color: #f8f9fa;
        border-radius: 0.3rem;
    }
    .card {
        transition: transform 0.2s;
    }
    .card:hover {
        transform: translateY(-5px);
    }
    .card-img-top {
        height: 200px;
        object-fit: cover;
    }
</style>