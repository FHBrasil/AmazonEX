<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="paymentInfo" required="true" type="de.hybris.platform.commercefacades.order.data.CCPaymentInfoData" %>
<%@ attribute name="requestSecurityCode" required="true" type="java.lang.Boolean" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set value="${not empty paymentInfo and not empty paymentInfo.billingAddress}" var="billingAddressOk"/>
<spring:theme code="checkout.summary.paymentMethod.securityCode.whatIsThis.description" var="securityWhatText"/>

<div class="summaryPayment clearfix"  data-security-what-text="${securityWhatText}">
    <ycommerce:testId code="checkout_paymentDetails_text">
            <div class="column append-1">
				 <strong><spring:theme code="checkout.summary.paymentMethod.header" htmlEscape="false"/></strong>
                <ul>
                    <li>${fn:escapeXml(paymentInfo.accountHolderName)}</li>
                    <li>${fn:escapeXml(paymentInfo.cardNumber)}</li>
                    <li>${fn:escapeXml(paymentInfo.cardTypeData.name)}</li>
                    <li><spring:theme code="checkout.summary.paymentMethod.paymentDetails.expires" arguments="${paymentInfo.expiryMonth},${paymentInfo.expiryYear}"/></li>
                </ul>
                <c:if test="${requestSecurityCode}">
				 <form>
					 <div class="control-group security">
						 <label for="SecurityCode"><spring:theme code="checkout.summary.paymentMethod.securityCode"/>*</label>
						 <div class="controls">
							<input type="text" class="text security" id="SecurityCode"/>
							<a href="#" class="security_code_what"><spring:theme code="checkout.summary.paymentMethod.securityCode.whatIsThis"/></a>
						 </div>
					 </div>
				 </form>
                </c:if>
            </div>
		
            <div class="column">
                <ul>
                    <c:if test="${billingAddressOk}">
                        <li><strong><spring:theme code="checkout.summary.paymentMethod.billingAddress.header"/></strong></li>
                        <li><c:if test="${not empty paymentInfo.billingAddress.firstName}">Nome:&nbsp; ${fn:escapeXml(paymentInfo.billingAddress.firstName)}&nbsp; ${fn:escapeXml(paymentInfo.billingAddress.lastName)}</c:if></li>	
						<li><c:if test="${not empty paymentInfo.billingAddress.phone}">Telefone:&nbsp; (${fn:escapeXml(paymentInfo.billingAddress.dddPhone)})${fn:escapeXml(paymentInfo.billingAddress.phone)}</c:if></li>
						<li><c:if test="${not empty paymentInfo.billingAddress.celPhone}">Tel Celular:&nbsp; (${fn:escapeXml(paymentInfo.billingAddress.dddCelPhone)})${fn:escapeXml(paymentInfo.billingAddress.celPhone)}</c:if></li>
						<li>Endereço:&nbsp; ${fn:escapeXml(paymentInfo.billingAddress.line1)}&nbsp; ${fn:escapeXml(paymentInfo.billingAddress.number)}</li>
						<li><c:if test="${not empty paymentInfo.billingAddress.complement}">Complemento:&nbsp; ${fn:escapeXml(paymentInfo.billingAddress.complement)}</c:if></li>
						<li><c:if test="${not empty paymentInfo.billingAddress.reference}">Referência:&nbsp; ${fn:escapeXml(paymentInfo.billingAddress.reference)}</c:if></li>
						<li>Cep:&nbsp; ${fn:escapeXml(paymentInfo.billingAddress.postalCode)}</li>
						<li>Bairro:&nbsp; ${fn:escapeXml(paymentInfo.billingAddress.district)}</li>
						<li>Cidade:&nbsp; ${fn:escapeXml(paymentInfo.billingAddress.town)}<c:if test="${not empty paymentInfo.billingAddress.region.name}">-${fn:escapeXml(paymentInfo.billingAddress.region.name)}</c:if></li>
						<li>País:&nbsp; ${fn:escapeXml(paymentInfo.billingAddress.country.name)}</li>		
                    </c:if>
                </ul>
            </div>
    </ycommerce:testId>
    <ycommerce:testId code="checkout_changePayment_element">
	    <c:url value="/checkout/multi/add-payment-method" var="addPaymentMethodUrl"/>
        <a href="${addPaymentMethodUrl}" class="button positive editButton"><spring:theme code="checkout.summary.edit"/></a>
    </ycommerce:testId>
</div>
