<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<template:page pageTitle="${pageTitle}">
    <div id="breadcrumb" class="breadcrumb">
        <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
    </div>
    <div id="globalMessages">
        <common:globalMessages />
    </div>
    <nav:accountNav selected="payment-details" />
    <div class="column accountContentPane clearfix">
        <div class="headline">
            <spring:theme code="text.account.addPaymentDetails"
                text="Add Payment Details"/>
        </div>
        <div class="obrigatorio required">
            <spring:theme code="form.required"
                    text="Fields marked * are required"/>
        </div>
        <div id="checkoutContentPanel" class="clearfix" style="height: auto;">
            <form:form action="${request.contextPath}/my-account/edit-payment-details"
                    method="post" commandName="heringPaymentDetailsForm">
                <div id="cardDetails" class="cardForm" style="clear: both; display: block;">
                    <formElement:formInputBox
                            idKey="nameOnCard"
                            labelKey="text.account.paymentDetails.nameOnCard"
                            path="nameOnCard" mandatory="true"/>
                    <formElement:formInputBox
                            idKey="cardNumber"
                            labelKey="text.account.paymentDetails.cardNumber"
                            path="cardNumber" mandatory="true"/>
                    <div id="creditCardsPayment" class="control-group">
                        <ul>
                            <li>
                                <input type="radio" name="cardTypeCode" id="visa" 
                                        value="visa">
                                <label for="visa">Visa</label>
                            </li>
                            <li>
                                <input type="radio" name="cardTypeCode" id="mastercard"
                                        value="master">
                                <label for=master>Mastercard</label>
                            </li>
                            <li>
                                <input type="radio" name="cardTypeCode" id="diners"
                                        value="diners">
                                <label for="diners">Diners</label>
                            </li>
                            <li>
                                <input type="radio" name="cardTypeCode" id="american"
                                        value="amex">
                                <label for="amex">American</label>
                            </li>
                        </ul>
                    </div>
                    <formElement:formInputBox
                            idKey="cv2Number"
                            labelKey="text.account.paymentDetails.cv2Number"
                            path="cv2Number" mandatory="true"/>
                    <formElement:formSelectBox
                            idKey="expiryMonth"
                            labelKey="text.account.paymentDetails.expiryMonth"
                            path="expiryMonth" mandatory="true"
                            skipBlank="false" skipBlankMessageKey=""
                            items="${expiryMonths}"/>
                    <formElement:formSelectBox
                            idKey="expiryYear"
                            labelKey="text.account.paymentDetails.expiryYear"
                            path="expiryYear" mandatory="true"
                            items="${expiryYears}" />
                    <formElement:formInputBox
                            idKey="instalment"
                            labelKey="text.account.paymentDetails.installment"
                            path="instalment" mandatory="true"/>
                </div>
                <div id="paymentAddress" style="clear: both; margin-top: 30px;">
                    <label>
                        <spring:theme
                                code="text.account.paymentDetails.billingAddress"
                                text="Billing Address"/>
                    </label>
                    <div id="loadingAddress" style="float:right;display:none;z-index:100;">
						<img src="/store/_ui/desktop/common/images/colorbox/loading.gif"/>
					</div>
                    <formElement:formInputBox
                            idKey="billingAddress.receiver"
                            labelKey="text.account.paymentDetails.billingAddress.receiver"
                            path="billingAddress.receiver" mandatory="false"/>
                    <formElement:formInputBox
                            idKey="billingAddress.postcode"
                            labelKey="text.account.paymentDetails.billingAddress.postcode"
                            path="billingAddress.postcode" mandatory="true"/>
                    <div class="consultar-cep-link">
						<a target="_blank" href="http://www.buscacep.correios.com.br/servicos/dnec/menuAction.do?Metodo=menuEndereco">
							<spring:theme code="address.search.cep"
									text="Consultar CEP"/>
						</a>
					</div>
                    <formElement:formSelectBox
                            idKey="billingAddress.regionIso"
                            labelKey="text.account.paymentDetails.billingAddress.regionIso"
                            path="billingAddress.regionIso" mandatory="true"
                            itemLabel="name" itemValue="isocodeShort"
                            skipBlank="false" skipBlankMessageKey="form.select.empty"
                            items="${regions}"/>
                    <formElement:formInputBox
                            idKey="billingAddress.townCity"
                            labelKey="text.account.paymentDetails.billingAddress.townCity"
                            path="billingAddress.townCity" mandatory="true"/>
                    <formElement:formInputBox
                            idKey="billingAddress.neighborhood"
                            labelKey="text.account.paymentDetails.billingAddress.neighborhood"
                            path="billingAddress.neighborhood" mandatory="true"/>
                    <formElement:formInputBox
                            idKey="billingAddress.line1"
                            labelKey="text.account.paymentDetails.billingAddress.line1"
                            path="billingAddress.line1" mandatory="true"/>
                    <formElement:formInputBox
                            idKey="billingAddress.number"
                            labelKey="text.account.paymentDetails.billingAddress.number"
                            path="billingAddress.number" mandatory="true"/>
                    <formElement:formInputBox
                            idKey="billingAddress.complement"
                            labelKey="text.account.paymentDetails.billingAddress.complement"
                            path="billingAddress.complement" mandatory="false"/>
                </div>
                <div class="form-actions" style="clear: both; margin-top: 25px;">
                    <button type="button" class="negative bt-cancelar"
                            onclick="window.location='${profileUrl}'">
                        <spring:theme code="text.account.paymentDetails.cancel"
                                text="Cancel"/>
                    </button>
                    <button class="positive" type="submit">
                        <spring:theme 
                                code="text.account.paymentDetails.saveUpdates"
                                text="Save Updates"/>
                    </button>
                </div>
            </form:form>
        </div>
    </div>
</template:page>