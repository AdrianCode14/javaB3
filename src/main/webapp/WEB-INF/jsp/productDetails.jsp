<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="include/importTags.jsp"%>

<div class="container product-details">
    <h1 class="product-title">${product.getLocalizedLabel(locale.language)}</h1>

    <div class="product-description">
        <p>${product.getLocalizedDescription(locale.language)}</p>
    </div>

    <p class="price">
        <strong><spring:message code="cart.price" />:</strong> ${product.price} €
    </p>

    <form action="<c:url value='/cart/add'/>" method="post">
        <input type="hidden" name="productId" value="${product.productId}" />
        <input type="hidden" name="quantity" value="1" />
        <button type="submit" class="btn btn-primary" style="margin-bottom: 20px;">Ajouter au panier</button>
    </form>

    <div class="catalog-link">
        <form action="<spring:url value='/catalogue'/>" method="get">
            <button type="submit" class="btn btn-secondary">
                <spring:message code="link.catalog" />
            </button>
        </form>
    </div>

    <div class="general-info">
        <h2>Informations Générales</h2>
        <p>Ce livre est imprimé sur du papier de haute qualité et relié de manière professionnelle pour garantir une lecture agréable et durable. Chaque édition est conçue pour offrir une expérience de lecture optimale et une longue durée de vie.</p>

        <ul>
            <li>Garantie : 2 ans</li>
            <li>Livraison gratuite sous 3 à 5 jours ouvrés</li>
            <li>Retour gratuit sous 30 jours</li>
        </ul>
    </div>

    <div class="faq">
        <h2>Questions Fréquentes</h2>
        <p><strong>Quelle est la politique de retour ?</strong></p>
        <p>Vous pouvez retourner ce produit dans un délai de 30 jours à compter de la date de réception, à condition qu'il soit dans son état d'origine.</p>
    </div>
</div>

<style>
    .product-details {
        max-width: 800px;
        margin: auto;
    }
    .product-title {
        font-size: 2em;
        margin-bottom: 20px;
    }
    .product-image img {
        max-width: 100%;
        height: auto;
        display: block;
        margin: 0 auto 20px;
    }
    .product-description {
        font-size: 1.2em;
        margin-bottom: 20px;
    }
    .price {
        font-size: 1.5em;
        color: #d9534f;
        margin-bottom: 20px;
    }
    .catalog-link {
        margin-top: 20px;
    }
    .general-info, .faq {
        margin-top: 40px;
    }
    .general-info h2, .faq h2 {
        font-size: 1.5em;
        margin-bottom: 10px;
    }
    .general-info ul {
        list-style-type: none;
        padding: 0;
    }
    .general-info ul li {
        margin-bottom: 10px;
    }
</style>
