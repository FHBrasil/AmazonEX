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
<%@ taglib prefix="notifyme" tagdir="/WEB-INF/tags/desktop/notifyme" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/cart/add" var="addToCartUrl"/>
<c:if test="${themeName == 'black'}">
<style>

/* wishlist button */

.wish-button {
	float: left;
	width: 160px;
	height: 22px;
	padding: 10px 0 0 0px;
	clear: both;
	font-family:"Roboto";
	color:#999;
	font-size: 13px;
	text-transform: uppercase;
}

.wish-button2 {
	float: left;
	width: 160px;
	height: 22px;
	clear: both;
	font-family:"Roboto";
	color:#999;
	font-size: 13px;
	text-transform: uppercase;
}

.wish-button a {
	color:#636363;
	padding: 2px 0 0 25px;
	text-decoration:none;
	font-weight: normal
}

.w-button {
	border-top: 1px solid #ddd; 
	border-bottom: 1px solid #ddd; 
	width: 100%; 
	padding: 15px 0 7px 0;
	margin-top: 10px;
	margin-bottom: 10px;
}

.w-button2 {
	border-top: 1px solid #ddd; 
	border-bottom: 1px solid #ddd; 
	width: 100%; 
	padding: 15px 0 7px 25px;
	margin: 10px 0 10px 0;
	font-weight: normal;
}

.bt-wish-on {
	background: url("/store/_ui/desktop/common/images/add-w.png") no-repeat 0;
}

.bt-wish-off {
	background: url("/store/_ui/desktop/common/images/add-w-off.png") no-repeat 0;
	padding: 10px 0 0 25px;
}

.bt-wish-off2 {
	background: url("/store/_ui/desktop/common/images/add-w-off.png") no-repeat 0;
	
}

</style>
	<div class="qty">
		<script type="text/javascript">
		    document.addEventListener("DOMContentLoaded", function(){
		       document.getElementById("Quantity_More").addEventListener("click",function(){
		   	   qtyInput_${product.code}.value = parseInt(qtyInput_${product.code}.value) + 1;
		   	   qty_${product.code}.value = parseInt(qty_${product.code}.value) + 1;
		     }, false);
		   }, false);
		    
		    document.addEventListener("DOMContentLoaded", function(){
		    document.getElementById("Quantity_Less").addEventListener("click",function(){
			if(qtyInput_${product.code}.value >= 2)
			{	
				qtyInput_${product.code}.value = parseInt(qtyInput_${product.code}.value) - 1;
				qty_${product.code}.value = parseInt(qty_${product.code}.value) - 1;
		    }
		       }, false);
		    }, false);
		</script>
		<c:if test="${product.purchasable}">
			<form>
				<label for="qtyInput"><spring:theme code="basket.page.quantity"/></label>
				
				<a href="#" id="Quantity_Less"  class="productUpdateQuantityLess1" title="Remover 1 item">
					<spring:theme code="basket.page.update.less1"/>								
				</a>
				<input id="qtyInput_${product.code}" type="text" maxlength="3" size="1" name="qtyInput" class="qty" value="1">
				<a href="#" id="Quantity_More" class="productUpdateQuantityMore1" title="Adicionar 1 item">
					<spring:theme code="basket.page.update.more1"/>
				</a>
			</form>
		</c:if>
		<c:if test="${product.stock.stockLevelStatus.code eq 'lowStock'}">
			<c:if test="${product.stock.stockLevel < 4}">
				<c:set var="productStockLevel"><spring:theme code="product.variants.only.left" arguments="${product.stock.stockLevel}"/></c:set>
			</c:if>
		</c:if>

		<ycommerce:testId code="productDetails_productInStock_label">
			<p class="stock_message">${productStockLevel}</p>
		</ycommerce:testId>
	</div>
	
	<div class="productAddToCartPanel clearfix">
		<form:form id="code_${product.code}" method="post" class="add_to_cart_form" action="${addToCartUrl}" >
			<c:if test="${product.purchasable}">
				<input type="hidden" name="qty" class="qty" value="1" id="qty_${product.code}">
			</c:if>
			<input type="hidden" name="productCodePost" value="${product.code}"/>
			<c:if test="${allowAddToCart}">
				<c:set var="buttonType">button</c:set>
				<c:if test="${product.purchasable and product.stock.stockLevelStatus.code ne 'outOfStock' }">
					<c:set var="buttonType">submit</c:set>
				</c:if>
				<c:choose>
					<c:when test="${fn:contains(buttonType, 'button')}">
						<button type="${buttonType}" class="addToCartButton outOfStock" disabled="disabled">
							<spring:theme code="product.variants.out.of.stock"/>
						</button>
					</c:when>
					<c:otherwise>
						<button id="addToCartButton" type="${buttonType}" class="addToCartButton" disabled="disabled">
							<spring:theme code="basket.add.to.basket"/>	
						</button>
					</c:otherwise>
				</c:choose>
			</c:if>
		</form:form>
		<c:if test="${product.stock.stockLevelStatus.code eq 'outOfStock'}">
			<notifyme:notifymeRegister product="${product}" />
		</c:if>
		<c:if test="${allowAddToCart}">
	        <c:choose>
	        	<c:when test="${not added}">
	            	<p class="wish-button w-button">
	               		<c:url value="/w/${product.code}" var="addToWishlistUrl"/>
	                    <a href="${addToWishlistUrl}/addToWishlist" id="add_to_wishlist" class="bt-wish-on">
	                    	<spring:theme code="product.product.details.addToWishlist" text="Add to wishlist"/>
	                    </a>
	                </p>
	            </c:when>
	            <c:otherwise>
	                <p class="wish-button2 bt-wish-off2 w-button2">
	                    <spring:theme code="product.product.details.onWishlist" text="On wishlist"/>
	                </p>
	            </c:otherwise>
	        </c:choose>
        </c:if>
	</div>
</c:if>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<c:choose>
	<c:when test="${product.purchasable && product.stock.stockLevelStatus.code != 'outOfStock'}">
	<form:form id="code_${product.code}" method="post" action="${addToCartUrl}" class="addToCartForm">
	<section class="clear">
		<div class="quantidade">
            <input class="productStock" type="hidden" value="${product.stock.stockLevel}" />
			<c:if test="${product.purchasable}">
				<h3><spring:theme code="basket.page.quantity"/>:</h3>
				<input id="qtyInput_${product.code}" type="number" value="1" min="1" maxlength="3" name="qty">
			</c:if>
			<c:if test="${product.stock.stockLevelStatus.code eq 'lowStock'}">
				<c:if test="${product.stock.stockLevel < 4}">
					<c:set var="productStockLevel"><spring:theme code="product.variants.only.left" arguments="${product.stock.stockLevel}"/></c:set>
				</c:if>
			</c:if>
	
			<ycommerce:testId code="productDetails_productInStock_label">
				<span>${productStockLevel}</span>
			</ycommerce:testId>
		</div>
		
		<product:productSocialSharing />
	</section>
		<div class="productAddToCartPanel clearfix">
			<input type="hidden" name="productCodePost" value="${product.code}"/>
			<c:if test="${allowAddToCart}">
				<button type="${buttonType}" class="addToCartButton btn btn-big add">
					<spring:theme code="cart.page.add"/>	
				</button>
			</c:if>
		</div>
	</form:form>
	</c:when>
	<c:otherwise>
		<notifyme:notifymeRegister product="${product}" />
	</c:otherwise>
	</c:choose>
</c:if>