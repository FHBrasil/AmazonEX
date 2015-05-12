<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<spring:theme code="text.addToCart" var="addToCartText"/>
<spring:theme code="text.popupCartTitle" var="popupCartTitleText"/>
<c:url value="/cart" var="cartUrl"/>
<c:url value="/cart/checkout" var="checkoutUrl"/>

<c:if test="${themeName == 'black'}">
	<c:if test="${!(empty numberItemsInCart or numberItemsInCart eq 0)}">
		<div style="padding:15px">
			<c:if test="${numberShowing > 0 }">
				<div class="legend">
					<spring:theme code="popup.cart.showing" arguments="${numberShowing},${numberItemsInCart}"/>
					<c:if test="${numberItemsInCart > numberShowing}">
						<span class="mostrar_todos"><a href="${cartUrl}">mostrar todos</a></span>
					</c:if>
				</div>
			</c:if>
			
			
			<c:if test="${empty numberItemsInCart or numberItemsInCart eq 0}">
				<div class="cart_modal_popup empty-popup-cart">
					<spring:theme code="popup.cart.empty"/>
				</div>
			</c:if>
			<c:if test="${numberShowing > 0 }">
				<ul class="itemList">
					<c:forEach items="${entries}" var="entry" end="${numberShowing - 1}">
						<c:url value="${entry.product.url}" var="entryProductUrl"/>
						<li class="popupCartItem">
							<div class="itemThumb">
								<a href="${entryProductUrl}">
									<product:productPrimaryImage product="${entry.product}" format="cartIcon"/>
								</a>
							</div>
							<div class="itemDesc">
								<a class="itemName" href="${entryProductUrl}">${entry.product.name}</a>
								<div class="itemQuantity">
								<span class="label"><spring:theme code="popup.cart.quantity"/></span>
									${entry.quantity}
								</div>
				
								<!-- Remove Button -->
								<script type="text/javascript">
								    document.addEventListener("DOMContentLoaded", function(){
							        document.getElementById("RemoveProduct_${entry.entryNumber}").addEventListener("click",function(){
							        	window.alert("-");
							        	window.alert("quantity${entry.entryNumber}.value " + quantity${entry.entryNumber}.value );
							        	window.alert("initialQuantity_${entry.entryNumber}" + initialQuantity_${entry.entryNumber}.value);
							        	quantity${entry.entryNumber}.value = 0;
							        	document.forms["updateCartForm${entry.entryNumber}"].submit();
							        	
								       }, false);
							    }, false);
							    
							    </script>
							    <c:url value="/cart/update" var="cartUpdateFormAction" />
								<form:form id="updateCartForm${entry.entryNumber}" action="${cartUpdateFormAction}" method="post" commandName="updateQuantityForm${entry.entryNumber}">
									<input type="hidden" name="entryNumber" value="${entry.entryNumber}"/>
									<input type="hidden" name="productCode" value="${entry.product.code}"/>
									<input type="hidden" name="initialQuantity" value="${entry.quantity}"/>
									<input type="hidden" name="eraseQuantity_${entry.entryNumber}" id="eraseQuantity_${entry.entryNumber}" value="0"/>
									
									<input  type="hidden" id="quantity${entry.entryNumber}" name="quantity" value="0" >
										<c:if test="${entry.updateable}" >
											<div style="float:left">
												<ycommerce:testId code="cart_product_removeProduct">
												<div class=popup-cart-remove-icon>
													<spring:theme code="text.iconCartRemove" var="iconCartRemove"/>
														<a href="#" style="margin-left: 0px!important;" onclick="document.forms['updateCartForm${entry.entryNumber}'].submit()" id="RemoveProduct_${entry.entryNumber}" class="submitRemoveProduct" title="Remover" >
															${iconCartRemove}
														</a>
												</div>
												</ycommerce:testId>
											</div>
										</c:if>
								</form:form>

								<c:forEach items="${entry.product.baseOptions}" var="baseOptions">
									<c:forEach items="${baseOptions.selected.variantOptionQualifiers}" var="baseOptionQualifier">
										<c:if test="${baseOptionQualifier.qualifier eq 'style' and not empty baseOptionQualifier.image.url}">
											<div class="itemColor">
												<span class="label"><spring:theme code="product.variants.colour"/></span>
												<img src="${baseOptionQualifier.image.url}" alt="${baseOptionQualifier.value}" title="${baseOptionQualifier.value}"/>
											</div>
										</c:if>
										<c:if test="${baseOptionQualifier.qualifier eq 'size'}">
											<div class="itemSize">
												<span class="label"><spring:theme code="product.variants.size.show"/></span>
												${baseOptionQualifier.value}
											</div>
										</c:if>
									</c:forEach>
								</c:forEach>
								
								<c:if test="${not empty entry.deliveryPointOfService.name}">
									<div class="itemPickup"><span class="itemPickupLabel"><spring:theme code="popup.cart.pickup"/></span>${entry.deliveryPointOfService.name}</div>
								</c:if>
								<div class="itemPrice"><format:price priceData="${entry.basePrice}"/></div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</c:if>
			<c:if test="${not empty cartData.deliveryCost}">
				<div  class="total">
					Frete&nbsp;<span class="right"><format:price priceData="${cartData.deliveryCost}"/></span>
				</div>
			</c:if>
			<div  class="total">
				<spring:theme code="popup.cart.total"/>&nbsp;<span class="right"><format:price priceData="${cartData.totalPrice}"/></span>
			</div>
			
			<div  class="banner">
				<c:if test="${not empty lightboxBannerComponent && lightboxBannerComponent.visible}">
						<cms:component component="${lightboxBannerComponent}" evaluateRestriction="true"  />
				</c:if>
			</div>
			
			<div class="links">
				<a href="${cartUrl}" class="button positive"><spring:theme code="checkout.checkout" /></a>
			</div>
			<script src="https://nxtck.com/act.php?tag=XXXXX"></script>
		</div>
	</c:if>
</c:if>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<div class="mini-carrinho logo-bar">
		<c:if test="${numberShowing > 0 }">
			<ul class="products">
				<c:forEach items="${entries}" var="entry" end="${numberShowing - 1}">
					<c:url value="${entry.product.url}" var="entryProductUrl"/>
				<%-- BRAND IMAGE --%>
					<li>
						<div class="brand">
							<product:productBrand product="${entry.product}"/>
						</div>
						<a href="${entryProductUrl}" class="thumbMiniCart"><product:productPrimaryImage product="${entry.product}" format="thumbnail"/></a>
						<div class="info">
							<h2><a href="${entryProductUrl}">${entry.product.name}</a></h2>
							<ul>
							<c:set value="false" var="colorIsUsed"/>
							<c:forEach items="${entry.product.baseOptions}" var="option">
								<c:forEach items="${option.selected.variantOptionQualifiers}" var="selectedOption">
									<c:if test="${selectedOption.name == 'Tamanho'}">
										<li>
											${selectedOption.name}:
											${selectedOption.value}
										<li>
									</c:if>
									<c:if test="${selectedOption.name == 'Cor' && colorIsUsed == false}">
										<c:set value="true" var="colorIsUsed"/>
										<li>
											<div style="float:left">${selectedOption.name}:</div>
											<div class="${selectedOption.value}" style="background-color:${entry.product.color.RGB}; width:12px !important; height:12px !important;float:left;margin-left:5px"></div>
										</li>
									</c:if>
								</c:forEach>
							</c:forEach>
							</ul>
							<div class="precos">
								<c:choose>
								<c:when test="${entry.product.fromPrice}">
								<s>
									<spring:theme code="product.volumePrices.from" /> 
									<spring:theme code="product.currencynotation" />
									<fmt:formatNumber value="${entry.product.oldPrice}" type="number" minFractionDigits="2" />
								</s>
								</c:when>
								<c:otherwise>
								</c:otherwise>
								</c:choose>
								<strong>por: <format:fromPrice priceData="${entry.basePrice}" /></strong>
							</div>
						</div>
						<c:url value="/cart/update" var="cartUpdateFormAction" />
						<form:form id="updateCartForm${entry.entryNumber}" action="${cartUpdateFormAction}" method="post" commandName="updateQuantityForm${entry.entryNumber}">
							<input type="hidden" name="entryNumber" value="${entry.entryNumber}"/>
							<input type="hidden" name="productCode" value="${entry.product.code}"/>
							<input type="hidden" name="initialQuantity" value="${entry.quantity}"/>
							<input type="hidden" name="eraseQuantity_${entry.entryNumber}" id="eraseQuantity_${entry.entryNumber}" value="0"/>
							<input type="hidden" id="quantity${entry.entryNumber}" name="quantity" value="0" >
							<c:if test="${entry.updateable}" >
								<ycommerce:testId code="cart_product_removeProduct">
									<spring:theme code="text.iconCartRemove" var="iconCartRemove"/>
									<a href="#" onclick="document.forms['updateCartForm${entry.entryNumber}'].submit()" id="RemoveProduct_${entry.entryNumber}" class="submitRemoveProduct" title="Remover" >
										<i class="fa fa-times"></i>
									</a>		
								</ycommerce:testId>
							</c:if>
						</form:form>					
					</li>				
				</c:forEach>
			</ul>
		</c:if>
		<div class="actions">
			<c:set var="hasItems" value="${not (empty numberItemsInCart or numberItemsInCart eq 0)}" />
			<div class="info">
 				<c:if test="${not hasItems}">
					<div class="empty-popup-cart">
						<i class="fa fa-meh-o"></i>
						<spring:theme code="popup.cart.empty"/>
					</div>
				</c:if>
				<c:if test="${hasItems}">
					<c:if test="${numberItemsInCart gt 1}">
						<p>${totalItems} itens inclusos em sua sacola somam o valor:</p>
						<strong><format:price priceData="${cartData.totalPrice}"/></strong>
					</c:if>
					<c:if test="${numberItemsInCart lt 2}">
						<p>${totalItems} item incluso em sua sacola soma o valor:</p>
						<strong><format:price priceData="${cartData.totalPrice}"/></strong>
					</c:if>
					<c:if test="${numberItemsInCart > numberShowing}">
						<!--<div class="legend">
							<span class="mostrar_todos"><a href="${cartUrl}">mostrar todos</a></span>
						</div>-->
					</c:if>
				</c:if>
			</div>
			<c:if test="${hasItems}">
				<div class="btn-group">
					<a href="${cartUrl}" class="btn btn-full-bag">Sacola completa</a>
					<a href="${checkoutUrl}" class="btn btn-checkout">Finalizar compra</a>
				</div>
			</c:if>
		</div>
	</div>
</c:if>