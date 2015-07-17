<%@ taglib prefix="spring"		uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" 			uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ycommerce"	uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" 		uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="format" 		tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" 	tagdir="/WEB-INF/tags/desktop/product"%>
<%@ attribute name="order" 		required="true" type="de.hybris.platform.commercefacades.order.data.OrderData" %>

<div class="col-sm-4">
	<h1><spring:theme code="text.fliegercommerce.texto145"/></h1>
	<p><spring:theme code="text.fliegercommerce.texto146"/>&nbsp;${email}</p>
	<p><b><spring:theme code="text.fliegercommerce.texto147"/>&nbsp;${order.code}</b></p>
	<c:choose>
		<c:when test="${order.paymentMode.code == 'Advance'}">
			<p><spring:theme code="text.fliegercommerce.texto148"/></p>
			<dl>
				<dt><spring:theme code="text.fliegercommerce.texto149"/></dt>
				<dd><spring:theme code="text.fliegercommerce.texto156"/></dd>
				<dt><spring:theme code="text.fliegercommerce.texto150"/></dt>
				<dd><spring:theme code="orderConfirmation.Iban"/></dd>
				<dt><spring:theme code="text.fliegercommerce.texto151"/></dt>
				<dd><spring:theme code="orderConfirmation.Bic"/></dd>
				<dt><spring:theme code="text.fliegercommerce.texto152"/></dt>
				<dd>${order.totalPrice.formattedValue}</dd>
				<dt><spring:theme code="text.fliegercommerce.texto153"/></dt>
				<dd><spring:theme code="orderConfirmation.Usage"/></dd>
			</dl>
			<a href="javascript:window.print()" class="btn btn-default"><spring:theme code="text.fliegercommerce.texto154"/></a>
			<p class="small margin-top"><spring:theme code="text.fliegercommerce.texto155"/></p>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
</div>

<%-- 
<div id="modal-order-resume">
	<div id="your-order" class="fancybox-content content" style="display: block;">
		<header>
			<h1><spring:theme code="order.resume"/></h1>
			<h2>${order.code}</h2>
		</header>
		
		<section id="products-list">
			<ul>
			<c:forEach items="${order.entries}" var="entry" >
			<c:set var="product" value="${entry.product}" />
				<li class="product-details">
					<product:productPrimaryImage product="${product}" format="cartIcon"/>
					
					<ul>
						<li class="titulo">${product.name}</li>
						<li class="tamanho"><spring:theme code="product.variants.size.show"/>: ${product.size}</li>
						<li class="cor">
									<spring:theme code="product.variants.color"/>:
									<div class="cores">
										<ul>
											<li style="background: ${product.color.RGB}">
												<div class="tooltip" style="display: none;">
													${product.color.fullDescription }
												</div>
											</li>
										</ul>
									</div>
						</li>
						<li class="codigo"><spring:theme code="order.product.code"/> ${product.code }</li>
						<li class="precos">
							<c:choose>
								<c:when test="${product.oldPrice > product.price.value}">
									<!-- Have OLD PRICE -->
									<s>
										<spring:theme code="product.volumePrices.from" /> 
										<spring:theme code="product.currencynotation" /> 
										<fmt:formatNumber value="${product.oldPrice}" type="number" minFractionDigits="2" />
									</s>
									<strong> 
										<spring:theme code="product.volumePrices.to" />
										<format:fromPrice priceData="${product.price}" />
									</strong>
								</c:when>
								<c:otherwise>
									<strong><format:fromPrice priceData="${product.price}" /></strong>
								</c:otherwise>
							</c:choose>
						</li>
					</ul>
				</li>
			</c:forEach>
			</ul>
		</section>
		<section id="general-info" class="half">
			<div class="left">
				<section id="values">
					<header>
						<h2><spring:theme code="text.account.order.values"/></h2>
					</header>

					<dl>
						<dt><spring:theme code="order.items" arguments="${order.totalItems}"/></dt>
						<dd class="subtotal">
							<strong>${order.subTotal.formattedValue}</strong>
						</dd>
						<dt class="freight"><spring:theme code="order.totals.delivery"/></dt>
						<dd>
							<c:choose>
								<c:when test="${not empty order.deliveryCost.formattedValue}">
									${order.deliveryCost.formattedValue}
								</c:when>
								<c:otherwise>
									R$ 0,00
								</c:otherwise>
							</c:choose> 
							<small>
								<spring:theme code="order.delivery" arguments="${order.estimatedDeliveryDays}"/>
								<% /*${order.estimatedDeliveryDays}*/ %>
							</small>
						</dd>
						<dt class="discount"><spring:theme code="order.totals.savings"/></dt>
						<dd>${order.totalDiscounts.formattedValue}:</dd>
						<dt class="method"><spring:theme code="general.pagamento"/></dt>
						<dd class="method">
							<c:choose>
								<c:when test="${not empty order.paymentInfo }"><spring:theme code="text.fliegercommerce.texto53"/></c:when>
								<c:when test="${not empty order.customPaymentInfo }"><spring:theme code="text.fliegercommerce.texto54"/></c:when>
								<c:otherwise>Voucher</c:otherwise>
							</c:choose>
						</dd>
					</dl>
					<dl class="total">
						<dt class="total"><spring:theme code="order.total"/></dt>
						<dd class="total">
							<strong>${order.totalPrice.formattedValue}</strong>
						</dd>
					</dl>
				</section>
			</div>
			<div class="right">
				<section id="shipping">
					<header>
						<h2><spring:theme code="order.delivery.detail"/></h2>
					</header>

					<c:set var="adress" value="${order.deliveryAddress}" />
					<dl>
						<dt><spring:theme code="order.delivery.adress"/></dt>
						<dd>
							${adress.line1}, ${adress.number} - ${adress.complement} ${adress.district} - ${adress.town} - ${adress.region.isocodeShort}<br>
							<spring:theme code="order.delivery.postalcode"/> ${adress.postalCode}
						</dd>
						<dt><spring:theme code="order.delivery.type"/></dt>
						<dd>${adress.type}</dd>
						<c:if test="${not empty adress.reference}">
							<dt><spring:theme code="order.delivery.referencia"/></dt>
							<dd>${adress.reference}</dd>
						</c:if>
					</dl>
				</section>
			</div>
		</section>
	</div>
</div>
--%>