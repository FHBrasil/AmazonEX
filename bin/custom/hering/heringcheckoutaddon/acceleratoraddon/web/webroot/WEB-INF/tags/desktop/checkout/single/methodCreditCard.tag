<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<section id="payment-credit-card">
<input type="hidden" name="voucherAmountEqualsOrderAmount" value="${cartData.totalPrice.value eq 0.0 ? true : false}" />
<form:hidden path="paymentId" class="create_update_payment_id" />
<form:hidden path="instalment" />
<input type="radio" name="cardTypeCode" id="pay-visa" value="visa">
<label class="btn btn-default " for="pay-visa">VISA</label>
<input type="radio" name="cardTypeCode" id="pay-mastercard" value="master">
<label class="btn btn-default " for="pay-mastercard">MasterCard</label>
<form:hidden path="paymentId" class="create_update_payment_id" />
<div class="form-group">
	<formElement:formInputBox idKey="cardNumber" labelKey="payment.cardNumber"
		path="cardNumber" inputCSS="form-control text required-payment required-numbers" mandatory="true" autocomplete="off" />
</div>								
<div class="form-group">
	<formElement:formInputBox idKey="nameOnCard" labelKey="payment.nameOnCard"
    	path="nameOnCard" inputCSS="form-control text required-payment required-letters" mandatory="true" />
</div>
<label for="cardmonth"><spring:theme code="payment.expiryDate" /></label>			
<div class="form-group row">
	<div class="col-xs-6">									
		<formElement:formSelectBox idKey="cardmonth" path="expiryMonth" 
			mandatory="false" skipBlank="true" skipBlankMessageKey="" items="${months}"	selectCSSClass="form-control required-payment" />
	</div>									
	<div class="col-xs-6">
		<formElement:formSelectBox idKey="cardyear" path="expiryYear"
			mandatory="false" skipBlank="true" skipBlankMessageKey="" items="${expiryYears}" selectCSSClass="form-control required-payment" />
	</div>
</div>
<div class="form-group row">
	<div class="col-xs-6">
		<label for="cv2Number">
			<spring:theme code="payment.cv2Number" />			
        </label>								
		<input id="cv2Number" name="cv2Number" class="form-control text required-payment required-numbers" type="text" maxlength="5" autocomplete="off" />
	</div>
	<div class="col-xs-6" style="padding-top: 30px;">
		<a href="#" class="hover-tooltip"> 
			<span class="glyphicon glyphicon-question-sign"></span>
			<spring:theme code="checkout.oQueIsso" text="O que e isso" />
            <div class="tooltip information-content securityCodeInfo" style="display: none !important;">
		      	<h3> <spring:theme code="checkout.toolTip.CodSeguranca" /> </h3>
			    <ul class="code-information">
			      	<li>
			          	<h3>Visa, Mastercard, DinersClub, Hipercard</h3> 
			            <img src="/_ui/desktop/theme-${themeName}/images/card-safe-code.png" alt="">
			            <p> <spring:theme code="checkout.tooltip.tresDigitos" text="Encontra no verso 3 digitos" /> </p>
			        </li>
			        <li class="amex">
			          	<h3>American Express</h3>
			            <img src="/_ui/desktop/theme-${themeName}/images/card-safe-code-american-express.png" alt="">
			            <p> <spring:theme code="checkout.tooltip.quatroDigitos" text="Encontra na frente 4 digitos" /> </p>
			        </li>
				</ul>
			</div>
		</a>
	</div>
</div>
</section>