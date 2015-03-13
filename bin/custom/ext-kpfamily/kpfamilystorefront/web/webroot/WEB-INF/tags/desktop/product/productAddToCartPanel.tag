<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="allowAddToCart" required="true" type="java.lang.Boolean" %>
<%@ attribute name="isMain" required="false" type="java.lang.Boolean" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/desktop/action" %>

<c:url value="/cart/add" var="addToCartUrl"/>
<c:set var="stockLevel" value="${product.stock.stockLevel}"/> <%-- set variant max stock, not product --%>

<form name="addToCartForm" action="${addToCartUrl}" method="POST">
    <input type="hidden" id="maxStock" value="${stockLevel}" />
    <input type="hidden" name="productCodePost" value="${product.code}"/>
    <div class="col-xs-4 margin-top qty150126">
        <div class="form-group row">
            <div class="col-md-4 hidden-xs hidden-sm">
                <label class="" for="qty">
                    <spring:theme code="product.quantity" text="Menge"/>:
                </label>
            </div>
            <div class="col-xs-12 col-md-8">
                <input id="qty" name="qty" class="form-control input-lg" type="number" min="1"
                        value="1" />
            </div>
        </div>
    </div>
    <div class="col-xs-8 margin-top">
        <c:set var="disableAddToCartButton" value="disabled='disabled'" />
        <c:if test="${product.purchasable and product.stock.stockLevel > 0}">
            <c:set var="disableAddToCartButton" value="iusiqbdifqyewbv"/>
        </c:if>
        <%-- TODO: open <cart:cartModal> on addToCartButton click --%>
        <button id="addToCartButton" class="btn btn-primary btn-lg" type="button" href="#cartModal"
                data-toggle="modal" ${disableAddToCartButton}>
            <spring:theme code="basket.add.to.basket" text="In den Warenkorb" />
        </button>
        <br />
        <button class="btn btn-link" type="button">
            <span class="glyphicon glyphicon-heart"></span>
            <spring:theme code="text.wishlist.add" text="Auf die Wunschliste" />
        </button>
    </div>
</form>


<!-- <div class="qty"> -->
<%-- 	<c:if test="${product.purchasable}"> --%>
<!-- 		<label for="qtyInput"> -->
<%-- 			<spring:theme code="basket.page.quantity"/> --%>
<!-- 		</label> -->
<!-- 		<input type="text" maxlength="3" size="1" id="qtyInput" name="qtyInput" class="qty" value="1"> -->
<%-- 	</c:if> --%>

<%-- 	<c:if test="${product.stock.stockLevel gt 0}"> --%>
<%-- 		<c:set var="productStockLevel">${product.stock.stockLevel}&nbsp; --%>
<%-- 			<spring:theme code="product.variants.in.stock"/> --%>
<%-- 		</c:set> --%>
<%-- 	</c:if> --%>
<%-- 	<c:if test="${product.stock.stockLevelStatus.code eq 'lowStock'}"> --%>
<%-- 		<c:set var="productStockLevel"> --%>
<%-- 			<spring:theme code="product.variants.only.left" arguments="${product.stock.stockLevel}"/> --%>
<%-- 		</c:set> --%>
<%-- 	</c:if> --%>
<%-- 	<c:if test="${product.stock.stockLevelStatus.code eq 'inStock' and empty product.stock.stockLevel}"> --%>
<%-- 		<c:set var="productStockLevel"> --%>
<%-- 			<spring:theme code="product.variants.available"/> --%>
<%-- 		</c:set> --%>
<%-- 	</c:if> --%>
<%-- 	<ycommerce:testId code="productDetails_productInStock_label"> --%>
<%-- 		<p class="stock_message">${productStockLevel}</p> --%>
<%-- 	</ycommerce:testId> --%>
<!-- </div> -->
<%-- <div id="actions-container-for-${component.uid}" class="productAddToCartPanelContainer clearfix"> --%>
<!-- 	<ul class="productAddToCartPanel clearfix"> -->
<%-- 		<action:actions element="li" styleClass="productAddToCartPanelItem span-5" parentComponent="${component}"/> --%>
<!-- 	</ul> -->
<!-- </div> -->

