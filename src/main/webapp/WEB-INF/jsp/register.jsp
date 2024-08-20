<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Enregistrement Utilisateur</title>
</head>
<body>
<h2>Enregistrement Utilisateur</h2>

<c:if test="${not empty error}">
  <div style="color: red;">${error}</div>
</c:if>

<form:form method="POST" modelAttribute="user" action="${pageContext.request.contextPath}/register">
  <table>
    <tr>
      <td><label for="email">Email:</label></td>
      <td>
        <form:input path="email" id="email" type="email" required="true" />
        <form:errors path="email" cssStyle="color: red;" />
      </td>
    </tr>
    <tr>
      <td><label for="firstName">Prénom:</label></td>
      <td>
        <form:input path="firstName" id="firstName" required="true" />
        <form:errors path="firstName" cssStyle="color: red;" />
      </td>
    </tr>
    <tr>
      <td><label for="lastName">Nom:</label></td>
      <td>
        <form:input path="lastName" id="lastName" required="true" />
        <form:errors path="lastName" cssStyle="color: red;" />
      </td>
    </tr>
    <tr>
      <td><label for="phoneNumber">Numéro de téléphone:</label></td>
      <td>
        <form:input path="phoneNumber" id="phoneNumber" type="tel" />
        <form:errors path="phoneNumber" cssStyle="color: red;" />
      </td>
    </tr>
    <tr>
      <td><label for="deliveryAddress">Adresse de livraison:</label></td>
      <td>
        <form:textarea path="deliveryAddress" id="deliveryAddress" rows="3" />
        <form:errors path="deliveryAddress" cssStyle="color: red;" />
      </td>
    </tr>
    <tr>
      <td><label for="password">Mot de passe:</label></td>
      <td>
        <form:password path="password" id="password" required="true" />
        <form:errors path="password" cssStyle="color: red;" />
      </td>
    </tr>
    <tr>
      <td><label for="passwordConfirm">Confirmer le mot de passe:</label></td>
      <td>
        <form:password path="passwordConfirm" id="passwordConfirm" required="true" />
        <form:errors path="passwordConfirm" cssStyle="color: red;" />
      </td>
    </tr>
    <tr>
      <td><label for="nickname">Surnom (facultatif):</label></td>
      <td>
        <form:input path="nickname" id="nickname" />
        <form:errors path="nickname" cssStyle="color: red;" />
      </td>
    </tr>
    <tr>
      <td><label for="favoriteColor">Couleur préférée (facultatif):</label></td>
      <td>
        <form:input path="favoriteColor" id="favoriteColor" />
        <form:errors path="favoriteColor" cssStyle="color: red;" />
      </td>
    </tr>
    <tr>
      <td colspan="2"><input type="submit" value="S'inscrire" /></td>
    </tr>
  </table>
</form:form>
</body>
</html>