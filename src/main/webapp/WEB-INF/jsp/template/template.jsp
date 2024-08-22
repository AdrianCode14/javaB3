<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="../include/importTags.jsp"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title><spring:message code="site.title" /></title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="<c:url value='/css/first.css' />" />
</head>
<body>
<header class="bg-dark text-white py-3">
  <div class="container">
    <div class="row align-items-center">
      <div class="col-md-6">
        <h1 class="mb-0">BookShop</h1>
      </div>
      <div class="col-md-6 text-end">
        <div class="language-selector">
          <a href="?lang=fr"><img src="<c:url value='/images/fr.png'/>" alt="Français" class="img-fluid" style="max-width: 20px;" /></a>
          <a href="?lang=en"><img src="<c:url value='/images/engl.png'/>" alt="English" class="img-fluid" style="max-width: 20px;" /></a>
        </div>
      </div>
    </div>
  </div>
</header>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto">
        <li class="nav-item"><a class="nav-link" href="<spring:url value='/'/>"><spring:message code="link.home" /></a></li>
        <li class="nav-item"><a class="nav-link" href="<spring:url value='/societyDescription'/>"><spring:message code="link.society" /></a></li>
        <li class="nav-item">
          <a class="nav-link" href="<spring:url value='/catalogue'/>"><spring:message code="link.catalog" /></a>
        </li>
        <li class="nav-item"><a class="nav-link" href="<spring:url value='/cart'/>"><spring:message code="link.cart" /></a></li>
      </ul>
      <ul class="navbar-nav">
        <sec:authorize access="isAnonymous()">
          <li class="nav-item"><a class="nav-link" href="<spring:url value='/login'/>"><spring:message code="link.login" /></a></li>
          <li class="nav-item"><a class="nav-link" href="<spring:url value='/register'/>"><spring:message code="link.register" /></a></li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
          <li class="nav-item">
            <a class="nav-link" href="<spring:url value='/profile/edit'/>">
              <spring:message code="link.edit.profile" />
            </a>
          </li>
          <li class="nav-item">
            <form action="<spring:url value='/logout'/>" method="post" class="d-inline">
              <button type="submit" class="btn btn-link nav-link"><spring:message code="link.logout" /></button>
            </form>
          </li>
        </sec:authorize>
      </ul>
    </div>
  </div>
</nav>

<div class="container mt-4">
  <sec:authorize access="isAuthenticated()">
    <div class="alert alert-info">
      <spring:message code="welcome.message" arguments="${pageContext.request.userPrincipal.name}" />
    </div>
  </sec:authorize>

  <main>
    <tiles:insertAttribute name="main-content" />
  </main>
</div>

<footer class="bg-dark text-white py-3 mt-4">
  <div class="container text-center">
    <p>&copy; 2023 BookShop. <spring:message code="footer.rights" /></p>
  </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>