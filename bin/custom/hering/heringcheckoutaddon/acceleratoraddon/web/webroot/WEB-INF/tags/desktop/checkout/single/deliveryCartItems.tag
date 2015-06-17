<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="cartData" required="true"
    type="de.hybris.platform.commercefacades.order.data.CartData"%>
<%@ attribute name="showPotentialPromotions" required="false" type="java.lang.Boolean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>

<ol class="list150608">
	<c:set var="sharp" value='#'/>
	<p class="underlinelinks150212">Es gelten unsere <a href="#">Allgemeinen Gesch&auml;ftsbedingungen</a>. Sie haben ein <a href="#">14-t&auml;giges Widerrufsrecht</a>. Hier finden Sie <a href="#">Hinweise zum Datenschutz</a>.</p>
	<c:forEach items="${cartData.entries}" var="entry">
    	<li class="item150608">
			<div class="row">
				<div class="col-xxs-4 col-xs-12 col-md-4">
                	<a href="${entry.product.url}" class="image150608"><product:productPrimaryImage product="${entry.product}" format="cartIcon" /></a>
                </div>
                <div class="col-xxs-8 col-xs-12 col-md-8">
                	<a href="${entry.product.url}">${entry.product.name}</a>
                	<br /><small>${sharp}${entry.product.code}</small>                	
	                <c:if test="${not empty entry.product.size}">
	                    <div class="tamanho"><spring:theme code="text.fliegercommerce.texto51"/>: ${entry.product.size}</div>
	                </c:if>                   
	                <c:if test="${not empty entry.product.color}">
	                    <spring:theme code="checkout.single.details.cartItems.color" />:
	                    <div class="${entry.product.color.RGB}" style="background-color:${entry.product.color.RGB};"></div>
	                </c:if>                    
                	<div class="row">
                		<div class="col-xs-6">
                			<small>${entry.quantity}x
                			<c:url value="/cart/update" var="cartUpdateFormAction" />
                			<form:form id="updateCartForm${entry.entryNumber}" action="${cartUpdateFormAction}" method="post" commandName="updateQuantityForm${entry.entryNumber}">
		                        <input type="hidden" name="entryNumber" value="${entry.entryNumber}" />
		                        <input type="hidden" name="productCode" value="${entry.product.code}" />
		                        <input type="hidden" name="initialQuantity" value="${entry.quantity}" />
		                        <input type="hidden" name="initialQuantity_${entry.entryNumber}" id="initialQuantity_${entry.entryNumber}" value="${entry.quantity}" />
		                        <ycommerce:testId code="cart_product_quantity">
		                            <form:input disabled="${not entry.updateable}" type="number" size="1" id="quantity${entry.entryNumber}" class="qty" path="quantity"
		                                min="1" maxlength="3" required="required" step="1" />
		                        </ycommerce:testId>
		                    </form:form>
                				<c:choose>
		                        	<c:when test="${entry.product.fromPrice}">
		                            	<s>
		                            		<spring:theme code="checkout.single.details.cartItems.productPrice.from" />:
		                                    <fmt:formatNumber value="${entry.product.oldPrice}" type="number" minFractionDigits="2" />
		                                </s>
		                                <strong>
		                                	<spring:theme code="checkout.single.details.cartItems.productPrice.to" />:
		                                    <format:price priceData="${entry.basePrice}" displayFreeForZero="true" />
		                                </strong>
		                             </c:when>
		                             <c:otherwise>
		                             	<format:price priceData="${entry.basePrice}" displayFreeForZero="true" />
		                             </c:otherwise>
		                    	</c:choose>
                			</small>
                		</div>
                		<div class="col-xs-6 text-right">	                		
	                    	<strong><format:price priceData="${entry.totalPrice}" displayFreeForZero="true" /></strong>
                    	</div>
                	</div>                	
                </div>
			</div>
		</li>
		<li id="toggleDelivery" class="delivery160608 collapse out">
			<small><span class="glyphicon glyphicon-stop text-onstock"></span> <b>Expressversand:</b> Lieferung Morgen, 17.08.2015</small>
		</li>
	</c:forEach>
	<li class="delivery160608 onstock">
		<small><span class="glyphicon glyphicon-stop text-nostock"></span> Lieferung am Freitag, 17.08.2015</small>
	</li>
</ol>
<div class="checkbox">
	<label><input type="checkbox" data-toggle="collapse" data-target="#toggleDelivery,.itemdelivery160608"> Sofort lieferbare Positionen sofort versenden? (+3,95 &euro;)</label>
</div>