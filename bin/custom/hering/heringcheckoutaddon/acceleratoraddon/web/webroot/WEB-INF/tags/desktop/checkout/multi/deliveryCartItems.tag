<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="cartData" required="true" type="de.hybris.platform.commercefacades.order.data.CartData" %>
<%@ attribute name="showPotentialPromotions" required="false" type="java.lang.Boolean" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>




<table class="deliveryCartItems">
	<thead>
		<tr>
			<td colspan="4"><spring:theme code="checkout.pickup.items.to.be.delivered"/></td>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach items="${cartData.entries}" var="entry">
			<c:if test="${entry.deliveryPointOfService == null}">
				<c:url value="${entry.product.url}" var="productUrl"/>
				<tr>
					<td rowspan="2" class="thumb">
						<a href="${productUrl}">
							<product:productPrimaryImage product="${entry.product}" format="cartIcon"/>
						</a>
					</td>
					<td colspan="3" class="desc">
						<div class="name">
								${entry.product.name}
						</div>
						
						
						
						<c:if test="${not empty entry.product.size}">
							<div class="property-1">
								<strong>Tamanho:</strong>
								<span>${entry.product.size}</span>
							</div>
							<div class="clear"></div>
						</c:if>
						<c:if test="${not empty entry.product.color}">
							<div class="property-2">
								<strong>Cor:</strong>
								<span>${entry.product.color.fullDescription}</span>
							</div>
							<div class="clear"></div>
						</c:if>
						
						
						<c:if test="${ycommerce:doesPotentialPromotionExistForOrderEntry(cartData, entry.entryNumber) && showPotentialPromotions}">
							<ul class="cart-promotions">
								<c:forEach items="${cartData.potentialProductPromotions}" var="promotion">
									<c:set var="displayed" value="false"/>
									<c:forEach items="${promotion.consumedEntries}" var="consumedEntry">
										<c:if test="${not displayed && consumedEntry.orderEntryNumber == entry.entryNumber}">
											<c:set var="displayed" value="true"/>
											<li class="cart-promotions-potential"><span>${promotion.description}</span></li>
										</c:if>
									</c:forEach>
								</c:forEach>
							</ul>
						</c:if>
						
						
						<c:if test="${ycommerce:doesAppliedPromotionExistForOrderEntry(cartData, entry.entryNumber)}">
							<ul class="cart-promotions">
								<c:forEach items="${cartData.appliedProductPromotions}" var="promotion">
									<c:set var="displayed" value="false"/>
									<c:forEach items="${promotion.consumedEntries}" var="consumedEntry">
										<c:if test="${not displayed && consumedEntry.orderEntryNumber == entry.entryNumber}">
											<c:set var="displayed" value="true"/>
											<li class="cart-promotions-applied"><span>${promotion.description}</span></li>
										</c:if>
									</c:forEach>
								</c:forEach>
							</ul>
						</c:if>
					
					
					
					</td>
				</tr>
				<tr>
					<td class="priceRow">
					<c:choose>
					<c:when test="${entry.product.fromPrice}"> <!-- Have OLD PRICE And OLD PRICE Bigger-->
					
									<div class="price-from">	
												<spring:theme code="product.volumePrices.from" /> 
												<spring:theme code="product.currencynotation"/>
												<fmt:formatNumber value="${entry.product.oldPrice}" type="number" minFractionDigits="2" />
									</div>  
											<div class="price-to">
												<spring:theme code="product.volumePrices.to"/> <format:fromPrice priceData="${entry.basePrice}" />
											</div>
									
								
									<!-- Parcels -->
<%-- 									<c:if test="${entry.product.parcelUnitPrice gt 0}"> --%>
										
<%-- 											<div class="price-parcel-cart"> ${entry.product.priceParcels}<spring:theme code="product.volumePrices.parcel"/></div>  --%>
<%-- 											<div class="price-parcel-price-cart"><spring:theme code="product.currencynotation"/><fmt:formatNumber value="${entry.product.parcelUnitPrice}" type="number" minFractionDigits="2" /> </div> --%>
									
<%-- 									</c:if> --%>
							</c:when>
							<c:otherwise><!-- Does Not Have OLD PRICE -->
									<format:price priceData="${entry.basePrice}" displayFreeForZero="true"/>
							</c:otherwise>
					</c:choose>
						
					
					</td>
					<td class="priceRow"><spring:theme code="basket.page.qty"/>:  ${entry.quantity}</td>
					<td class="priceRow"><format:price priceData="${entry.totalPrice}" displayFreeForZero="true"/></td>
				</tr>
			</c:if>
		</c:forEach>
	</thead>
</table>


