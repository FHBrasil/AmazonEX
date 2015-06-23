<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="galleryImages" required="true" type="java.util.List" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>

<spring:theme code="text.addToCart" var="addToCartText"/>

<!--  chaordic - inicio -->
<div class="productData">
	<input type="hidden" class="productCode" value="${product.code}"/>
	<input type="hidden" class="productName" value="${product.name}"/>
	<input type="hidden" class="productDescription" value="${product.description}"/>
	<input type="hidden" class="productUrl" value="${product.url}"/>
	<input type="hidden" class="productValue" value="${product.price.value}"/>
	<input type="hidden" class="productOldPrice" value="${product.oldPrice}"/>
	<input type="hidden" class="productAvailable" value="${product.availableForPickup}"/>
	
	<c:set var="categories" value="" />
	<c:forEach items="${product.categories }" var="cat">
		<c:choose>
			<c:when test="${categories == ''}">
				<c:set var="categories" value="${cat.name}" />
			</c:when>
			<c:when test="${categories != ''}">
				<c:set var="categories" value="${categories}, ${cat.name}" />
			</c:when>
		</c:choose>
	</c:forEach>
	<input type="hidden" class="productCategories" value="${categories}" />
       <input type="hidden" class="productGender" value="${product.genders[0].name}" />
</div>
<!--  chaordic - fim -->
<section id="product-main">
	<div class="margin-top">
		<div class="col-xs-12 col-sm-6 v-bottom">
			<product:productImagePanel product="${product}" galleryImages="${galleryImages}"/> 
		</div>
		<div class="rightcol-sm-6 v-bottom margin-top">
			<div class="col-sm-12 text-right hidden-xs">
			</div>
			<div class="wrap pro-info">
				<ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
					<header>
						<h1><b>[BRAND]</b>${product.name}</h1>
						<span><spring:theme code="product.code"/>&nbsp;${product.code}</span>
					</header>
				</ycommerce:testId>
				
				<c:if test="${product.purchasable && product.stock.stockLevelStatus.code != 'outOfStock'}">
				
				<ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
					<product:productPricePanel product="${product}"/>
				</ycommerce:testId>
				
				<%--<product:productReviewSummary product="${product}"/>--%>
		
				<c:if test="${not empty product.summary}">
					<div class="summary">
						${product.summary}
					</div>
				</c:if>
		
				<product:productPromotionSection product="${product}"/>
		
				<c:if test="${not empty product.color}">
					<div class="product-color-RGB">
				<!--  	Cor RGB: ${product.color.RGB}-->
					</div>
				</c:if>
				</c:if>
				
				<cms:pageSlot position="VariantSelector" var="component" element="div">
					<cms:component component="${component}"/>
				</cms:pageSlot>
					
				<cms:pageSlot position="AddToCart" var="component" element="div" class="span-8">
					<cms:component component="${component}"/>
				</cms:pageSlot>
				
				<product:productDetailsFooter product="${product}" />
			</div>
		</div>
		<cms:pageSlot position="Section2" var="feature" element="div" class="span-8 section2 cms_disp-img_slot last">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
	</div>
	<div class="clearfix"></div>
</section>
