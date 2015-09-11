<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="customPaymentInfo" required="true" type="br.hering.facades.order.data.CustomPaymentInfoData" %>
<%@ attribute name="paymentModeName" required="true" type="java.lang.String"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set value="${not empty customPaymentInfo and not empty customPaymentInfo.billingAddress}" var="billingAddressOk"/>
<spring:theme code="checkout.summary.paymentMethod.securityCode.whatIsThis.description" var="securityWhatText"/>

<div class="summaryPayment clearfix"  data-security-what-text="${securityWhatText}">
    <ycommerce:testId code="checkout_paymentDetails_text">
            <div class="column append-1">          	
				<strong><spring:theme code="checkout.summary.paymentMethod.header" htmlEscape="false"/></strong>
                ${paymentModeName}
            </div>
			<c:if test="${billingAddressOk}">
            <div class="column">
                <ul>
                    <c:if test="${billingAddressOk}">
                       <li><strong><spring:theme code="checkout.summary.paymentMethod.billingAddress.header"/></strong></li>
                        <li>Nome:&nbsp; ${fn:escapeXml(customPaymentInfo.billingAddress.firstName)}&nbsp; ${fn:escapeXml(customPaymentInfo.billingAddress.lastName)}</li>
						<li>Telefone:&nbsp; (${fn:escapeXml(customPaymentInfo.billingAddress.dddPhone)})${fn:escapeXml(customPaymentInfo.billingAddress.phone)}</li>
						<li><c:if test="${not empty customPaymentInfo.billingAddress.celPhone}">Tel Celular:&nbsp; (${fn:escapeXml(customPaymentInfo.billingAddress.dddCelPhone)})${fn:escapeXml(customPaymentInfo.billingAddress.celPhone)}</c:if></li>
						<li>Endereço:&nbsp; ${fn:escapeXml(customPaymentInfo.billingAddress.line1)}&nbsp; ${fn:escapeXml(customPaymentInfo.billingAddress.number)}</li>
						<li>Complemento:&nbsp; ${fn:escapeXml(customPaymentInfo.billingAddress.complement)}</li>
						<li><c:if test="${not empty customPaymentInfo.billingAddress.reference}">Referência:&nbsp; ${fn:escapeXml(customPaymentInfo.billingAddress.reference)}</c:if></li>
						<li>Cep:&nbsp; ${fn:escapeXml(customPaymentInfo.billingAddress.postalCode)}</li>
						<li>Bairro:&nbsp; ${fn:escapeXml(customPaymentInfo.billingAddress.district)}</li>
						<li>Cidade:&nbsp; ${fn:escapeXml(customPaymentInfo.billingAddress.town)}<c:if test="${not empty customPaymentInfo.billingAddress.region.name}">-${fn:escapeXml(customPaymentInfo.billingAddress.region.name)}</c:if></li>
						<li>País:&nbsp; ${fn:escapeXml(customPaymentInfo.billingAddress.country.name)}</li>		
                    </c:if>
                </ul>
            </div>
            </c:if>
    </ycommerce:testId>
    <ycommerce:testId code="checkout_changePayment_element">
	    <c:url value="/checkout/multi/custom-add-payment-method" var="addPaymentMethodUrl"/>
        <a href="${addPaymentMethodUrl}" class="button positive editButton"><spring:theme code="checkout.summary.edit"/></a>
    </ycommerce:testId>
</div>
