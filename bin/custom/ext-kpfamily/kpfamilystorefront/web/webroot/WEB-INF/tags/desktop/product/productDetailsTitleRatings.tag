<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true"
    type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ attribute name="showShortDescription" required="true" type="Boolean" %>
<%@ attribute name="showBrandLogo" required="true" type="Boolean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:if test="${showBrandLogo}">
    <div class="col-sm-12 text-right hidden-xs">
        <a href="#"><img src="product/brand.jpg"></a>
     </div>
</c:if>
<h1>
    <b>${product.manufacturer}</b> ${product.name}
</h1>
<p>
    <span class="glyphicon stars">
        <span style="width: 90%"></span>
    </span>
     ${product.averageRating}(${product.numberOfReviews})
    <a href="#"><spring:theme code="review.write.review" text="Bewertung schreiben"/></a>
</p>
<c:if test="${showShortDescription}">
    <p>
        ${product.description}
        <a href="#">
            <spring:theme code="product.description.read.more" text="weiterlesen"/>
        </a>
    </p>
</c:if>