<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="ycommerce" uri="/WEB-INF/tld/ycommercetags.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:url value="/checkout/single/summary/createPaymentDetails.json" var="createPaymentUrl" />

<script type="text/javascript">
/*<![CDATA[*/
	$(document).ready(function() {
		//bindCycleFocusEvent();
	})
	
	function bindCycleFocusEvent()
	{
		$('#lastInTheForm').blur(function() {
			$('#paymentDetailsForm [tabindex$="10"]').focus();
		})
	}
/*]]>*/
</script>

<div class="title_holder">
	<div class="title">
		<div class="title-top">
			<span></span>
		</div>
	</div>
	<h2><spring:theme code="checkout.summary.paymentMethod.addPaymentDetails.header"/></h2>
</div>

<div class="item_container">
	
	<form:form method="post" commandName="klarnaPaymentInfoForm" action="${createPaymentUrl}" class="create_update_payment_form">
		<common:globalMessages/>
		<div class="payment_details_left_col">
			<h1><spring:theme code="checkout.summary.paymentMethod.klarna.paymentmodes"/></h1>
			
			<p><strong><spring:theme code="checkout.summary.paymentMethod.klarna.choose.paymentmodes"/></strong></p>			
			<p>	
				<ycommerce:testId code="checkout_deliveryModes">
					<dl id="klarnapayment_modes_dl">						
						<c:if test="${empty allowedKlarnaPaymentModes}">
							<spring:theme code="text.checkout.noKlarnaPaymentModes"/>
						</c:if>						
						<c:if test="${not empty allowedKlarnaPaymentModes}">
							<c:forEach items="${allowedKlarnaPaymentModes}" var="klarnaPaymentMode" varStatus="varStatus">
								<dt class="left">
									<c:choose>
										<c:when test="${varStatus.index == 0 || klarnaPaymentMode.selected}">									
											<input type="radio" name="paymentModeCode" value="${klarnaPaymentMode.code}" checked="checked"/>
										</c:when>					
										<c:otherwise>
											<input type="radio" name="paymentModeCode" value="${klarnaPaymentMode.code}" />					
										</c:otherwise>
									</c:choose>
									<c:if test="${klarnaPaymentMode.code == 'klarnaInvoice'}">									
										<label for='${klarnaPaymentMode.code}'>${klarnaPaymentMode.name} - <spring:theme code="checkout.summary.selected.paymentMethod.description.klarnaInvoice" arguments="${formattedKlarnaInvoiceFee}" argumentSeparator="#~/@!£$%^" /></label>
										<div id="paymentmode_klarna_link_to_invoice">
											<img src="${commonResourcePath}${countrySpecificKlarnaInvoiceLogo}" style="float: left; margin-right: 6px; margin-left: 26px;">
										</div>		
										<script type="text/javascript">
							             new Klarna.Terms.Invoice({
							                 el: 'paymentmode_klarna_link_to_invoice',
							                 eid: ${merchantId},
							                 country: '${currentKlarnaCountryIsoCode}',
							                 charge: ${klarnaInvoiceFee}
							             })
										</script>	
									</c:if>
									<c:if test="${klarnaPaymentMode.code == 'klarnaAccount'}">
										<label for='${klarnaPaymentMode.code}'>${klarnaPaymentMode.name} - <spring:theme code="checkout.summary.selected.paymentMethod.description.klarnaAccount"/></label>
										<div id="paymentmode_klarna_link_to_account">
											<div style="float: left; margin-right: 6px; margin-left: 26px;"><img src="${commonResourcePath}${countrySpecificKlarnaAccountLogo}"></div><div style="float: left; margin-right: 6px;"></div>
										</div>
										<div>
											<br/>
											<c:if test="${not empty creditWarningLogoNL}"><div style="float: left; margin-right: 6px; margin-left: 26px;"><img width="215px" height="25px" border="2" src="${commonResourcePath}${creditWarningLogoNL}"></div></c:if>
										</div>
										<script type="text/javascript">
							             new Klarna.Terms.Account({
							                 el: 'paymentmode_klarna_link_to_account',
							                 eid: ${merchantId},
							                 country: '${currentKlarnaCountryIsoCode}',
							                 charge: 0
							             })
										</script>
									</c:if>
									<c:if test="${klarnaPaymentMode.code == 'klarnaCampaign'}">
										<label for='${klarnaPaymentMode.code}'>${klarnaPaymentMode.name} - <spring:theme code="checkout.summary.selected.paymentMethod.description.klarnaCampaign"/></label>
										<div id="paymentmode_klarna_link_to_campaign">
											<img src="${commonResourcePath}${countrySpecificKlarnaAccountLogo}" style="float: left; margin-right: 6px; margin-left: 26px;">
										</div>
										<script type="text/javascript">
							             new Klarna.Terms.Special({
							                 el: 'paymentmode_klarna_link_to_campaign',
							                 eid: ${merchantId},
							                 country: '${currentKlarnaCountryIsoCode}',
							                 charge: 0
							             })
										</script>	
									</c:if>
								</dt>
								<dd></dd>
							</c:forEach>
						</c:if>					
					</dl>
				</ycommerce:testId>									
			</p>
			
			<p><strong><spring:theme code="checkout.summary.paymentMethod.klarnaDeliveryAddress.important"/></strong></p>			
			
			<p><strong><spring:theme code="checkout.summary.paymentMethod.klarnaDeliveryAddress"/></strong></p>
			<ul>
				<c:if test="${klarnaDeliveryAddress != null}">
					<li><spring:theme code="payment.address.name" />&nbsp;${fn:escapeXml(klarnaDeliveryAddress.title)}&nbsp;${fn:escapeXml(klarnaDeliveryAddress.firstName)}&nbsp;${fn:escapeXml(klarnaDeliveryAddress.lastName)}</li>
					<c:if test="${not empty klarnaDeliveryAddress.line2}">
						<li><spring:theme code="payment.address.street" />&nbsp;${fn:escapeXml(klarnaDeliveryAddress.line1)}&nbsp;${fn:escapeXml(klarnaDeliveryAddress.line2)}</li>
					</c:if>
					<c:if test="${empty klarnaDeliveryAddress.line2}">
						<li><spring:theme code="payment.address.street" />&nbsp;${fn:escapeXml(klarnaDeliveryAddress.line1)}</li>
					</c:if>					
					<li><spring:theme code="payment.address.town" />&nbsp;${fn:escapeXml(klarnaDeliveryAddress.town)}</li>
					<li><spring:theme code="payment.address.postcode" />&nbsp;${fn:escapeXml(klarnaDeliveryAddress.postalCode)}</li>
					<li><spring:theme code="payment.address.country" />&nbsp;${fn:escapeXml(klarnaDeliveryAddress.country.name)}</li>
				</c:if>
			</ul>				
			
			
			<strong><spring:theme code="checkout.summary.paymentMethod.klarna.required"/></strong>			
			<dl>			
				<form:hidden path="addressId" class="create_update_address_id" status="${not empty createUpdateStatus ? createUpdateStatus : ''}"/>
				<formElement:formInputBox idKey="payment.klarnaPhoneNo" labelKey="payment.klarnaPhoneNo" path="klarnaPhoneNo" inputCSS="text" mandatory="true" tabindex="2"/>
				<c:if test="${!showPNO}">
					<dt><label><spring:theme code="payment.klarnaGender"/>*</label></dt>
					<dd>
						<form:radiobutton path="genderCode" value="MALE"/> <label style="float: left;"><spring:theme code="payment.klarnaGender.male"/></label> <form:radiobutton path="genderCode" value="FEMALE"/> <label style="float: left;"><spring:theme code="payment.klarnaGender.female"/></label></label>
					</dd>
					<template:errorSpanField path="klarnaBirthOfDate">
						<ycommerce:testId code="LoginPage_Item_payment.klarnaBirthOfDate">
							<dt>
								<label class="" for="payment.klarnaBirthOfDate">
									<spring:theme code="payment.klarnaBirthOfDate"/>
									<c:if test="${true}">
										<span class="mandatory">
											<spring:theme code="login.required" var="loginrequiredText" />
											<img width="5" height="6" alt="" title="" src="${commonResourcePath}/images/mandatory.gif">
										</span>
									</c:if> 
									(Format: ${klarnaBirthdayFormat})
									<span class="skip"><form:errors path="klarnaBirthOfDate"/></span>
								</label>
							</dt>							
							<dd>
								<form:input cssClass="text" id="payment.klarnaBirthOfDate" path="klarnaBirthOfDate" tabindex="3"/>
							</dd>
						</ycommerce:testId>
					</template:errorSpanField>					
				</c:if>			 
			</dl>
			
			<p><spring:theme code="form.required"/></p>
			
		</div>

		<div class="payment_details_right_col">
			<h1><spring:theme code="checkout.summary.deliveryAddress"/></h1>
			<p><spring:theme code="checkout.summary.paymentMethod.klarna.paymentadr.equals.deliveryadr"/></p>
		</div>
		
		<div class="save_payment_details" style="float: right;">			
			<ycommerce:testId code="checkout_useThesePaymentDetails_button">
				<button class="form left use_these_payment_details_button" tabindex="19" id="lastInTheForm">
					<spring:theme code="checkout.summary.paymentMethod.addPaymentDetails.useThesePaymentDetails"/>
				</button>
			</ycommerce:testId>
		</div>	
	</form:form>
</div>
