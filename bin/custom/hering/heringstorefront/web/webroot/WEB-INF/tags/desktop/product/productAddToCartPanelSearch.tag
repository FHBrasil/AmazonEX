<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="allowAddToCart" required="true" type="java.lang.Boolean" %>
<%@ attribute name="isMain" required="true" type="java.lang.Boolean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%-- DZARM STORE HEADER --%>
<c:if test="${themeName == 'black'}">
<%--
<form>
	<label for="qtyInput" id="qtyInput_${product.code}"><spring:theme code="basket.page.quantity"/></label>
	<input type="text" maxlength="3" size="1" name="qtyInput" class="qty" value="1">
</form>
--%>
<c:url value="${product.url}" var="productUrl"/>
<form:form id="id_${product.code}" class="add_to_cart_form" action="${productUrl}" >
	<div class="qty">
<%-- 	<c:if test="${product.stock.stockLevel gt 0}"> 
<%-- 		<c:set var="productStockLevel">${product.stock.stockLevel}&nbsp;<spring:theme code="product.variants.in.stock"/></c:set> --%>
<%-- 	</c:if> --%>
<%-- 	<c:if test="${product.stock.stockLevelStatus.code eq 'lowStock'}"> --%>
<%-- 		<c:set var="productStockLevel"><spring:theme code="product.variants.only.left" arguments="${product.stock.stockLevel}"/></c:set> --%>
<%-- 	</c:if> --%>
<%-- 	<c:if test="${product.stock.stockLevelStatus.code eq 'inStock' and empty product.stock.stockLevel}"> --%>
<%-- 		<c:set var="productStockLevel"><spring:theme code="product.variants.available"/></c:set> --%>
<%-- 	</c:if> --%>
	
<%-- 	<ycommerce:testId code="productDetails_productInStock_label"> --%>
<%-- 		<p class="stock_message">${productStockLevel}</p> --%>
<%-- 	</ycommerce:testId> --%>
	</div>
	<div class="productAddToCartPanel clearfix">
<%--  	<c:url value="/cart/add" var="addToCartUrl"/> --%>	
		<c:if test="${true}">
<%-- 	<c:if test="${product.purchasable}"> --%>
			<input type="hidden" name="qty" class="qty" value="1">
		</c:if>
		<input type="hidden" name="productCodePost" value="${product.code}"/>
	
		<c:if test="${allowAddToCart}">
			<c:set var="buttonType">button</c:set>
	
<%-- 		<c:if test="${true}"> --%>
<%-- 		<c:if test="${product.purchasable and product.stock.stockLevelStatus.code ne 'outOfStock' }"> --%>
<%-- 			<c:set var="buttonType">submit</c:set> --%>
<%-- 		</c:if> --%>
	
			<div class="SearchBuyButton">
			<c:choose>
				<c:when test="${fn:contains(buttonType, 'button')}">
				<div class="addToCartButtonSearch">
						<a href="${productUrl}">
							<spring:theme code="cart.page.shop"/>
						</a>
					</div>
	
				</c:when>
	
				<c:otherwise>
					<div class="addToCartButtonSearch">
						<a href="${productUrl}">
							<spring:theme code="cart.page.shop"/>
						</a>
					</div>
	
				</c:otherwise>
			</c:choose>
			</div>
		</c:if>
	</div>
</form:form>
</c:if>

<%-- HERING STORE HEADER --%>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">

<c:url value="${product.url}" var="productUrl"/>
<form:form id="id_${product.code}" class="add_to_cart_form" action="${productUrl}" >
	<div class="qty"></div>
	<div class="productAddToCartPanel clearfix">
		<c:if test="${true}">
			<input type="hidden" name="qty" class="qty" value="1">
		</c:if>
		<input type="hidden" name="productCodePost" value="${product.code}"/>
	
		<c:if test="${allowAddToCart}">
			<c:set var="buttonType">button</c:set>
	
			<div class="SearchBuyButton">
			<c:choose>
				<c:when test="${fn:contains(buttonType, 'button')}">
				<div class="addToCartButtonSearch">
						<a href="${productUrl}" class="add">
							<spring:theme code="cart.page.add"/>
						</a>
					</div>
	
				</c:when>
	
				<c:otherwise>
					<div class="addToCartButtonSearch">
						<a href="${productUrl}" class="add">
							<%-- <spring:theme code="cart.page.shop"/> --%>
							<spring:theme code="cart.page.add"/>
						</a>
					</div>
	
				</c:otherwise>
			</c:choose>
			</div>
		</c:if>
	</div>
</form:form>

</c:if>
<!-- <a href="#" class="add">Adicionar à sacola</a>   -->
