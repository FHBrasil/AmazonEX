<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="deliveryAddress" required="true" type="de.hybris.platform.commercefacades.user.data.AddressData" %>
<%@ attribute name="cartData" required="false" type="de.hybris.platform.commercefacades.order.data.CartData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="hasShippedItems" value="${cartData.deliveryItemsQuantity > 0}" />

<div class="summaryDeliveryAddress">
	<ycommerce:testId code="checkout_deliveryAddressData_text">
		<c:if test="${not hasShippedItems}">
			<spring:theme code="checkout.pickup.no.delivery.required"/>
		</c:if>
		
		<c:if test="${hasShippedItems}">
			<strong><spring:theme code="checkout.summary.deliveryAddress.header" htmlEscape="false"/></strong>
			<ul>
				<li>Nome:&nbsp; ${fn:escapeXml(deliveryAddress.firstName)}&nbsp; ${fn:escapeXml(deliveryAddress.lastName)}</li>
				<li><c:if test="${not empty deliveryAddress.receiver}">Destinatário:&nbsp; ${fn:escapeXml(deliveryAddress.receiver)}</c:if></li>
				<li>Telefone:&nbsp; (${fn:escapeXml(deliveryAddress.dddPhone)})${fn:escapeXml(deliveryAddress.phone)}</li>
				<li><c:if test="${not empty deliveryAddress.celPhone}">Tel Celular:&nbsp; (${fn:escapeXml(deliveryAddress.dddCelPhone)})${fn:escapeXml(deliveryAddress.celPhone)}</c:if></li>
				<li>Endereço:&nbsp; ${fn:escapeXml(deliveryAddress.line1)}&nbsp; ${fn:escapeXml(deliveryAddress.number)}</li>
				<li>Complemento:&nbsp; ${fn:escapeXml(deliveryAddress.complement)}</li>
				<li><c:if test="${not empty deliveryAddress.reference}">Referência:&nbsp; ${fn:escapeXml(deliveryAddress.reference)}</c:if></li>
				<li>Cep:&nbsp; ${fn:escapeXml(deliveryAddress.postalCode)}</li>
				<li>Bairro:&nbsp; ${fn:escapeXml(deliveryAddress.district)}</li>
				<li>Cidade:&nbsp; ${fn:escapeXml(deliveryAddress.town)}<c:if test="${not empty deliveryAddress.region.name}">-${fn:escapeXml(deliveryAddress.region.name)}</c:if></li>
				<li>País:&nbsp; ${fn:escapeXml(deliveryAddress.country.name)}</li>	
			</ul>
		</c:if>
	</ycommerce:testId>

	<c:if test="${hasShippedItems}">
		<ycommerce:testId code="checkout_changeAddress_element">
		    <c:url value="/checkout/multi/edit-delivery-address" var="editAddressUrl" />
			<a href="${editAddressUrl}/?editAddressCode=${deliveryAddress.id}" class="button positive editButton"><spring:theme code="checkout.summary.edit"/></a>
		</ycommerce:testId>
	</c:if>
</div>