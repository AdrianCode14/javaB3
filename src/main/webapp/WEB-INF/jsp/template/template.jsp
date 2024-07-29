<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %> <%@
include file="../include/importTags.jsp"%> <%@ taglib
uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
  <head>
    <spring:url var="localeFr" value="">
      <spring:param name="locale" value="fr" />
    </spring:url>
    <spring:url var="localeEn" value="">
      <spring:param name="locale" value="en" />
    </spring:url>

    <style>
      /* Styles pour la barre de navigation */
      header {
        background-color: #f2f2f2;
        padding: 10px 0;
      }

      .language-icons {
        position: absolute;
        top: 10px;
        right: 10px;
        display: flex;
        gap: 10px;
      }

      .language-icons img {
        width: 30px;
        height: 30px;
      }

      nav {
        text-align: center;
      }

      nav ul {
        list-style-type: none;
        margin: 0 auto; /* Pour centrer la liste horizontalement */
        padding: 0;
        display: inline-block;
      }

      nav ul li {
        display: inline;
        margin-right: 20px;
      }

      nav ul li:last-child {
        margin-right: 0;
      }

      nav ul li a {
        text-decoration: none;
        color: #333;
        font-weight: bold;
        font-size: 16px;
      }

      /* Styles pour la liste des catégories */
      .categories {
        position: relative;
        display: inline-block;
      }

      /* Styles pour la liste des catégories au survol */
      .categories .category-list {
        display: none;
        position: absolute;
        background-color: #fff;
        min-width: 120px;
        box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
        z-index: 1;
        text-align: left;
      }

      .categories:hover .category-list {
        display: block;
      }

      .categories .category-list a {
        color: #333;
        padding: 10px;
        display: block;
        text-decoration: none;
      }
    </style>
    <link
      type="text/css"
      href="<spring:url value='/css/first.css' />"
      rel="stylesheet"
    />
  </head>
  <body>
    <!-- Icônes pour changer la langue en haut à droite -->
    <div class="language-icons">
      <a href="${localeFr}">
        <img alt="fr" src='<spring:url value="/images/fr.png" />' />
      </a>
      <a href="${localeEn}">
        <img alt="en" src='<spring:url value="/images/engl.png"/>' />
      </a>
    </div>

    <!-- Barre de navigation -->
    <header>
      <nav>
        <ul>
          <!-- Liens pour les fonctionnalités -->
          <li>
            <a href="/firstSpring/login"><fmt:message key="link.login" /></a>
          </li>
          <li>
            <a href="/firstSpring/register"
              ><fmt:message key="link.register"
            /></a>
          </li>
          <li>
            <a href="/firstSpring/accueil"><fmt:message key="link.home" /></a>
          </li>
          <li>
            <a href="/firstSpring/society"
              ><fmt:message key="link.society"
            /></a>
          </li>
          <li class="categories">
            <a href="#">Catégories</a>
            <div class="category-list">
              <a href="/firstSpring/accueil/">Horreur</a>
              <a href="#">Science-fiction</a>
              <a href="#">Policier</a>
            </div>
          </li>
          <li><a href="/firstSpring/catalogue">Catalogue des Produits</a></li>
          <li><a href="/caddie">Contenu du Caddie</a></li>
          <!-- Ajoutez d'autres liens selon vos besoins -->
        </ul>
      </nav>
    </header>

    <!-- Contenu de la page spécifique -->
    <main>
      <div class="content">
        <tiles:insertAttribute name="main-content" />
      </div>
    </main>

    <footer></footer>
  </body>
</html>
