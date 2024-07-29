<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://www.springframework.org/tags/form"
prefix="form" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Enregistrement Utilisateur</title>
  </head>
  <body>
    <h2>Enregistrement Utilisateur</h2>

    <form:form
      method="POST"
      modelAttribute="user"
      action="/firstSpring/register"
    >
      <table>
        <tr>
          <td><label for="email">Email:</label></td>
          <td><form:input path="email" id="email" /></td>
        </tr>
        <tr>
          <td><label for="firstName">Prénom:</label></td>
          <td><form:input path="firstName" id="firstName" /></td>
        </tr>
        <tr>
          <td><label for="lastName">Nom:</label></td>
          <td><form:input path="lastName" id="lastName" /></td>
        </tr>
        <tr>
          <td><label for="phoneNumber">Numéro de téléphone:</label></td>
          <td><form:input path="phoneNumber" id="phoneNumber" /></td>
        </tr>
        <tr>
          <td><label for="deliveryAddress">Adresse de livraison:</label></td>
          <td><form:input path="deliveryAddress" id="deliveryAddress" /></td>
        </tr>
        <tr>
          <td><label for="password">Mot de passe:</label></td>
          <td><form:input path="password" id="password" /></td>
        </tr>
        <tr>
          <td>
            <label for="passwordConfirm">Confirmer le mot de passe:</label>
          </td>
          <td><form:input path="passwordConfirm" id="passwordConfirm" /></td>
        </tr>
        <tr>
          <td colspan="2"><input type="submit" value="Enregistrer" /></td>
        </tr>
      </table>
    </form:form>
  </body>
</html>
