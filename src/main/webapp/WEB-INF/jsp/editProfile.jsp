<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
  <!-- Rest of your form -->
  <h2>Éditer votre profil</h2>

  <c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
  </c:if>

  <form:form method="POST" modelAttribute="user" action="${pageContext.request.contextPath}/profile/edit">
    <div class="form-group">
      <label for="email">Email:</label>
      <form:input path="email" id="email" class="form-control" readonly="true"/>
    </div>
    <div class="form-group">
      <label for="firstName">Prénom:</label>
      <form:input path="firstName" id="firstName" class="form-control"/>
      <form:errors path="firstName" cssClass="text-danger"/>
    </div>
    <div class="form-group">
      <label for="lastName">Nom:</label>
      <form:input path="lastName" id="lastName" class="form-control"/>
      <form:errors path="lastName" cssClass="text-danger"/>
    </div>
    <div class="form-group">
      <label for="deliveryAddress">Adresse de livraison:</label>
      <form:textarea path="deliveryAddress" id="deliveryAddress" class="form-control" rows="3"/>
      <form:errors path="deliveryAddress" cssClass="text-danger"/>
    </div>
    <div class="form-group">
      <label for="phoneNumber">Numéro de téléphone:</label>
      <form:input path="phoneNumber" id="phoneNumber" class="form-control"/>
      <form:errors path="phoneNumber" cssClass="text-danger"/>
    </div>
    <div class="form-group">
      <label for="nickname">Surnom:</label>
      <form:input path="nickname" id="nickname" class="form-control"/>
      <form:errors path="nickname" cssClass="text-danger"/>
    </div>
    <div class="form-group">
      <label for="favoriteColor">Couleur préférée:</label>
      <form:input path="favoriteColor" id="favoriteColor" class="form-control"/>
      <form:errors path="favoriteColor" cssClass="text-danger"/>
    </div>
    <button type="submit" class="btn btn-primary">Mettre à jour le profil</button>
  </form:form>

  <h3 class="mt-5">Changer le mot de passe</h3>

  <c:if test="${not empty passwordError}">
    <div class="alert alert-danger">${passwordError}</div>
  </c:if>

  <form:form method="POST" modelAttribute="passwordChangeForm" action="${pageContext.request.contextPath}/profile/change-password">
    <div class="form-group">
      <label for="oldPassword">Ancien mot de passe:</label>
      <form:password path="oldPassword" id="oldPassword" class="form-control"/>
      <form:errors path="oldPassword" cssClass="text-danger"/>
    </div>
    <div class="form-group">
      <label for="newPassword">Nouveau mot de passe:</label>
      <form:password path="newPassword" id="newPassword" class="form-control"/>
      <form:errors path="newPassword" cssClass="text-danger"/>
    </div>
    <div class="form-group">
      <label for="newPasswordConfirm">Confirmer le nouveau mot de passe:</label>
      <form:password path="newPasswordConfirm" id="newPasswordConfirm" class="form-control"/>
      <form:errors path="newPasswordConfirm" cssClass="text-danger"/>
    </div>
    <button type="submit" class="btn btn-primary">Changer le mot de passe</button>
  </form:form>
</div>