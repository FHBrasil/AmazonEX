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
<c:if test="${themeName == 'black'}">
<style>
	.starsa140109 {
	    display: inline-block;
	    width: 80px;
	    margin-top: 5px;
	}
	
	.starsbg140109 {
	    font-family: "BVCustom",Arial,sans-serif !important;
	    font-weight: 400 !important;
	    position: relative;
	    display: block;
	    color: #E6E6E6 !important;
	    font-size: 18px !important;
	}
	
	.stars140109 {
	    font-family: "BVCustom",Arial,sans-serif !important;
	    font-weight: 400 !important;
	    position: relative !important;
	    overflow: hidden !important;
	    display: block;
	    margin-top: -18px;
	    color: #FFA700 !important;
	    font-size: 18px !important;
	}
	
	.social-space {width: 190px;}
	
	.pin-div {margin: -1px 7px 0px 0px !important; float: left;}
	
	.myrecs2 {margin: 30px 0 0 0 !important;}
	
</style>
	<div class="productDetailsPanel">
		<product:productImagePanel product="${product}" galleryImages="${galleryImages}"/>
	
		<div class="span-10 productDescription last">
	
			<ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
				<h1>
						${product.name}
				</h1>
				<c:if test="${product.averageRating > 0}">
					<!-- Product Stars -->
					<div class="starsa140109">
						<span class="starsbg140109">&#9733;&#9733;&#9733;&#9733;&#9733;</span> <!-- código ascii '&#9733' refere-se a '★' -->
						<span class="stars140109" style="width:${product.averageRating/5*100}%!important; margin-right: 16px;">&#9733;&#9733;&#9733;&#9733;&#9733;</span>
					</div>
				</c:if>
				<div class="product-code">
					<spring:theme code="product.code"/> ${product.code}	
				</div>		
			
			</ycommerce:testId>
			
			<ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
				<product:productPricePanel product="${product}"/>
			</ycommerce:testId>
			
			<product:productReviewSummary product="${product}"/>
	
	
			<div class="summary">
				${product.summary}
			</div>
	
			<product:productPromotionSection product="${product}"/>
	
			<c:if test="${not empty product.color}">
				<div class="product-color-RGB">
			<!--  	Cor RGB: ${product.color.RGB}-->
				</div>
			</c:if>
	
			<cms:pageSlot position="VariantSelector" var="component" element="div">
				<cms:component component="${component}"/>
			</cms:pageSlot>
	
			<cms:pageSlot position="AddToCart" var="component" element="div" class="span-8">
				<cms:component component="${component}"/>
			</cms:pageSlot>
			<c:if test="${true}">
			<product:productSocialSharing />
	        <script type="text/javascript" src="https://nxtck.com/act.php?tag=40872&pid=${product.code}"></script>         
			</c:if>
			<product:productShippingPrice product="${product}"/>
		</div>
	
		<cms:pageSlot position="Section2" var="feature" element="div" class="span-8 section2 cms_disp-img_slot last">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
	</div>
</c:if>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<section id="product-main">
		<c:if test="${themeName != 'dzarm' }">
			<product:productImagePanel product="${product}" galleryImages="${galleryImages}"/> 
		</c:if>
	
		<div class="right product-main-info">
			<div class="wrap pro-info">
				<ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
					<header>
						<h1>${product.name}</h1>
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
		<c:if test="${themeName != 'dzarm' }">
			<cms:pageSlot position="Section2" var="feature" element="div" class="span-8 section2 cms_disp-img_slot last">
				<cms:component component="${feature}"/>
			</cms:pageSlot>
		</c:if>
	</section>
</c:if>