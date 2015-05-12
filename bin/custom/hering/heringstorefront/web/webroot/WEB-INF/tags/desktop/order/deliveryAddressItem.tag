<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.OrderData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${themeName == 'black'}">
<c:set var="hasShippedItems" value="${order.deliveryItemsQuantity > 0}" />
<div class="orderBox address">
	<div class="headline"><spring:theme code="text.deliveryAddress" text="Delivery Address"/></div>
	<c:if test="${not hasShippedItems}">
		<spring:theme code="checkout.pickup.no.delivery.required"/>
	</c:if>
	<c:if test="${hasShippedItems}">		
		
		<ul class="informacoes-usuario"> 
			<li><strong>Nome:</strong>&nbsp; ${fn:escapeXml(order.deliveryAddress.firstName)}&nbsp; ${fn:escapeXml(order.deliveryAddress.lastName)}</li>
			<li><c:if test="${not empty order.deliveryAddress.receiver}"><strong>Destinatário:</strong>&nbsp; ${fn:escapeXml(order.deliveryAddress.receiver)}</c:if></li>
		</ul>
		
		<ul class="contato-usuario"> 
			<li><strong>Telefone:</strong>&nbsp; (${fn:escapeXml(order.deliveryAddress.dddPhone)})${fn:escapeXml(order.deliveryAddress.phone)}</li>
			<li><c:if test="${not empty order.deliveryAddress.celPhone}"><strong>Tel Celular:</strong>&nbsp; (${fn:escapeXml(order.deliveryAddress.dddCelPhone)})${fn:escapeXml(order.deliveryAddress.celPhone)}</c:if></li>
		</ul>
		
		<ul class="endereco-usuario"> 
			<li><strong>Endereço:</strong>&nbsp; ${fn:escapeXml(order.deliveryAddress.line1)}&nbsp; ${fn:escapeXml(order.deliveryAddress.number)}</li>
			<li><strong>Complemento:</strong>&nbsp; ${fn:escapeXml(order.deliveryAddress.complement)}</li>
			<li><c:if test="${not empty order.deliveryAddress.reference}"><strong>Referência:</strong>&nbsp; ${fn:escapeXml(order.deliveryAddress.reference)}</c:if></li>
			<li><strong>Cep:</strong>&nbsp; ${fn:escapeXml(order.deliveryAddress.postalCode)} - ${fn:escapeXml(order.deliveryAddress.town)}<c:if test="${not empty order.deliveryAddress.region.name}">-${fn:escapeXml(order.deliveryAddress.region.isocodeShort)}</c:if></li>
			<li><strong>Bairro:</strong>&nbsp; ${fn:escapeXml(order.deliveryAddress.district)}</li>
		</ul>
	</c:if>
</div>
</c:if>

<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<c:set var="hasShippedItems" value="${order.deliveryItemsQuantity > 0}" />
	<section id="shipping-address" class="section-block">
		<header>
			<h2>Detalhes de entrega:</h2>
		</header>
		<c:if test="${not hasShippedItems}">
			<spring:theme code="checkout.pickup.no.delivery.required"/>
		</c:if>
		<c:if test="${hasShippedItems}">		
			<dl>
				<dt>Nome:</dt>
					<dd>${fn:escapeXml(order.deliveryAddress.firstName)}&nbsp; ${fn:escapeXml(order.deliveryAddress.lastName)}</dd>
				<c:if test="${not empty order.deliveryAddress.receiver}">
					<dt>Destinatário:</dt> 
						<dd>${fn:escapeXml(order.deliveryAddress.receiver)}</dd>
				</c:if>
				<dt>Telefone:</dt> 
					<dd>(${fn:escapeXml(order.deliveryAddress.dddPhone)})${fn:escapeXml(order.deliveryAddress.phone)}</dd>
				<c:if test="${not empty order.deliveryAddress.celPhone}">
					<dt>Tel Celular:</dt>
						<dd>(${fn:escapeXml(order.deliveryAddress.dddCelPhone)})${fn:escapeXml(order.deliveryAddress.celPhone)}</dd>
				</c:if>
				<dt>Endereço:</dt>
					<dd>${fn:escapeXml(order.deliveryAddress.line1)}&nbsp; ${fn:escapeXml(order.deliveryAddress.number)}</dd>
				<dt>Complemento:</dt>
					<dd>${fn:escapeXml(order.deliveryAddress.complement)}</dd>
				<c:if test="${not empty order.deliveryAddress.reference}">
					<dt>Referência:</dt> 
						<dd>${fn:escapeXml(order.deliveryAddress.reference)}</dd>
				</c:if>
				<dt>Cep:</dt>
					<dd>${fn:escapeXml(order.deliveryAddress.postalCode)} - ${fn:escapeXml(order.deliveryAddress.town)}<c:if test="${not empty order.deliveryAddress.region.name}">-${fn:escapeXml(order.deliveryAddress.region.isocodeShort)}</c:if></dd>
				<dt>Bairro:</dt>
					<dd>${fn:escapeXml(order.deliveryAddress.district)}</dd>
			</dl>
		</c:if>
	</section>
</c:if>
						