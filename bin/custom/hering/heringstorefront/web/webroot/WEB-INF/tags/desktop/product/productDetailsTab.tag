<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<c:if test="${themeName == 'black'}">
<ycommerce:testId code="productDetails_content_label">
	<div class="productDescriptionText">
		${product.description}
	</div>
</ycommerce:testId>
<div class="productFeatureClasses">
	<div class="tabHead2"><spring:theme code="product.product.caracteristicas" /></div>
	<product:productDetailsClassifications product="${product}"/>
</div>
</c:if>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<div class="tab">
		<ycommerce:testId code="productDetails_content_label">
			<div class="desc">
				${product.description}
			</div>
		</ycommerce:testId>
		<h3><spring:theme code="product.product.caracteristicas" /></h3>
		<product:productDetailsClassifications product="${product}"/>
	</div>
</c:if>