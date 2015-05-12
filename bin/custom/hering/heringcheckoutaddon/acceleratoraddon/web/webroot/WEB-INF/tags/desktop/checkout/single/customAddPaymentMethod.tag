<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="cartData" required="false"   type="de.hybris.platform.commercefacades.order.data.CartData"%>
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
<%@ taglib prefix="multi-checkout"
    tagdir="/WEB-INF/tags/addons/b2ccheckoutaddon/desktop/checkout/multi"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%--c:url value="/checkout/multi/add-payment-method" var="choosePaymentMethodUrl"/--%>

<c:if test="${themeName == 'black'}">
    <div class="span-7 append-1">
        <style>
#startDate, #issueNum {
	display: none !important;
}
</style>
        <div class="headline">
            <%--spring:theme code="address.headline" text="Addressbook"/--%>
            2 - Pagamento
        </div>
        <form:form method="post" commandName="paymentDetailsForm"
            class="create_update_payment_form"
            action="${request.contextPath}/checkout/single/custom-add-payment-method">
            <c:set var="deliveryAddress" value="${cartData.deliveryAddress}" />
            <div class="hering_payment_method_list clearfix">
                <c:set var="count" value="0" scope="page" />
                <ul>
                    <c:forEach items="${paymentModes}" var="paymentMode">
                        <c:set var="count" value="${count + 1}" scope="page" />
                        <div class="payment_method${count}"
                            style="width: 300px; margin-top: 1px; ${(paymentMode.code eq 'CreditCard') ? 'height: 360px;':''}">
                            <c:set var="checked"
                                value="${!(paymentMode.code eq selectedPaymentMode) ? 'checked=\"checked\"' : ''}" />
                            <c:if test="${paymentMode.code != 'Voucher'}">
                                <input type="radio" name="paymentMode"
                                        value="${paymentMode.code}"
                                        ${checked}
                                        id="${paymentMode.name}" />
                                <label for="${paymentMode.name}">
                                    ${paymentMode.name} </label>
                            </c:if>
                            <c:if test="${paymentMode.code == 'CreditCard'}">
                                <div id="cardDetails" class="cardForm"
                                    style="display: block;">
                                    <form:hidden path="paymentId"
                                        class="create_update_payment_id" />
                                    <formElement:formInputBox idKey="cardNumber"
                                        labelKey="payment.cardNumber"
                                        path="cardNumber" inputCSS="text"
                                        mandatory="true" autocomplete="off" />
                                    <div id="creditCardsPayment">
                                        <ul>
                                            <li><input type="radio"
                                                name="cardTypeCode" id="visa"
                                                value="visa" /> <label
                                                for="visa">Visa</label></li>
                                            <li><input type="radio"
                                                name="cardTypeCode"
                                                id="mastercard" value="master" />
                                                <label for=master>Mastercard</label>
                                            </li>
                                            <li><input type="radio"
                                                name="cardTypeCode" id="diners"
                                                value="diners" /> <label
                                                for="diners">Diners</label></li>
                                            <li><input type="radio"
                                                name="cardTypeCode"
                                                id="american" value="amex">
                                                <label for="amex">American</label>
                                            </li>
                                        </ul>
                                    </div>
                                    <formElement:formSelectBox
                                        idKey="instalment"
                                        labelKey="payment.instalment"
                                        path="instalment" mandatory="true"
                                        skipBlank="true" items="${instalments}" />
                                    <formElement:formInputBox idKey="nameOnCard"
                                        labelKey="payment.nameOnCard"
                                        path="nameOnCard" inputCSS="text"
                                        mandatory="true" />
                                    <fieldset id="startDate" class="cardDate">
                                        <legend>
                                            <spring:theme
                                                code="payment.startDate" />
                                        </legend>
                                        <formElement:formSelectBox
                                            idKey="StartMonth"
                                            labelKey="payment.month"
                                            path="startMonth" mandatory="true"
                                            skipBlank="false"
                                            skipBlankMessageKey=""
                                            items="${months}" />
                                        <formElement:formSelectBox
                                            idKey="StartYear"
                                            labelKey="payment.year"
                                            path="startYear" mandatory="true"
                                            skipBlank="false"
                                            skipBlankMessageKey=""
                                            items="${startYears}" />
                                    </fieldset>
                                    <fieldset class="cardDate">
                                        <legend>
                                            <spring:theme
                                                code="payment.expiryDate" />
                                        </legend>
                                        <formElement:formSelectBox
                                            idKey="ExpiryMonth"
                                            labelKey="payment.month"
                                            path="expiryMonth" mandatory="true"
                                            skipBlank="false"
                                            skipBlankMessageKey=""
                                            items="${months}" />
                                        <formElement:formSelectBox
                                            idKey="ExpiryYear"
                                            labelKey="payment.year"
                                            path="expiryYear" mandatory="true"
                                            skipBlank="false"
                                            skipBlankMessageKey=""
                                            items="${expiryYears}" />
                                    </fieldset>
                                    <div id="issueNum">
                                        <formElement:formInputBox
                                            idKey="payment.issueNumber"
                                            labelKey="payment.issueNumber"
                                            path="issueNumber" inputCSS="text"
                                            mandatory="false" />
                                    </div>
                                    <div class="control-group ">
                                        <label class="control-label "
                                            for="cv2Number"> <spring:theme
                                                code="payment.cv2Number" /> <span
                                            class="mandatory">*</span> <span
                                            class="skip"></span>
                                            <div class="tooltip">
                                                <span class="tooltip-link">
                                                    (?) </span> <span
                                                    class="tooltip-content"></span>
                                            </div>
                                        </label>
                                        <div class="controls">
                                            <input id="cv2Number"
                                                name="cv2Number" class="text"
                                                type="text" autocomplete="off" />
                                        </div>
                                    </div>
                                    <span class="cvcImage"></span>
                                    <c:if test="${false}">
                                        <div class="save_payment_details">
                                            <sec:authorize
                                                ifNotGranted="ROLE_ANONYMOUS">
                                                <form:checkbox id="SaveDetails"
                                                    path="saveInAccount" />
                                                <label for="SaveDetails">
                                                    <spring:theme
                                                        code="checkout.multi.paymentMethod.addPaymentDetails.savePaymentDetailsInAccount" />
                                                </label>
                                            </sec:authorize>
                                        </div>
                                    </c:if>
                                </div>
                            </c:if>
                            <c:if test="${paymentMode.code == 'Voucher'}">
                                <label for="${paymentMode.name}">
                                    ${paymentMode.name} </label>
                                <div id="voucherPayment" class="payment">
                                    <div class="item_container">
                                        <formElement:formInputBox
                                            idKey="voucher"
                                            labelKey="payment.valeCredito"
                                            path="voucher" inputCSS="text"
                                            mandatory="false" />
                                        <%--input type="text" id="voucher"
	                                            name="voucher"
	                                            style="color: #aaa; width: 300px;"
	                                            onfocus="this.value='';this.style.color='#000';this.onfocus='';"
	                                            placeholder="DIGITE AQUI SEU CUPOM DE DESCONTO/VALE CREDITO" /--%>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </c:forEach>
                </ul>
            </div>
        </form:form>
    </div>
</c:if>

<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
    <header><h2><spring:theme code="checkout.single.payment.title"/></h2></header>
<%--     <form:form method="post" commandName="paymentDetailsForm" --%>
<%--             class="create_update_payment_form" --%>
<%--             action="${request.contextPath}/checkout/single/placeOrder"> --%>
        <c:forEach items="${paymentModes}" var="paymentMode">
			<input type="hidden" name="voucherAmountEqualsOrderAmount"
					value="${cartData.totalPrice.value eq 0.0 ? true : false}" />
            <c:set var="checked"
                    value="${(paymentMode.code eq 'CreditCard') ? 'checked=\"checked\"' : ''}"/>
            <div class="section-container">
                <c:if test="${paymentMode.code == 'CreditCard'}">
                    <section id="payment-credit-card">
                        <header>
                            <label>
                                <input type="radio" name="paymentMode"
                                    value="${paymentMode.code}"
                                    ${checked}
                                    id="${paymentMode.name}"
                                    class="required-payment">
                                ${paymentMode.name}
                            </label>
                        </header>
                        <div class="obs">
                        	<spring:theme code="checkout.camposObrigatorios"
                        			text="Campos Obrigatorios"/>
            			</div>
                        <form:hidden path="paymentId"
                                class="create_update_payment_id" />
                        <div class="f-row" id="CreditCardInstalment">
                            <formElement:formSelectBox idKey="numero-parcelas"
                                labelKey="payment.instalment" path="instalment"
                                mandatory="true" skipBlank="true"
                                items="${instalments}" />
                        </div>
                        <div class="f-row">
                            <formElement:formInputBox idKey="cardNumber"
                                labelKey="payment.cardNumber" path="cardNumber"
                                inputCSS="text required-payment required-numbers"
                                mandatory="false" autocomplete="off" />
                        </div>
                        <style>.card-flags input{display:none;}</style>
                        <div class="f-row card-flags">
                            <ul>
                                <li class="hering disabled">
                                    <input type="radio" name="cardTypeCode"
                                            id="hering" value="hering" />
                                </li>
                                <li class="hipercard disabled">
                                    <input type="radio" name="cardTypeCode"
                                            id="hipercard" value="hipercard" />
                                </li>
                                <li class="visa disabled">
                                    <input type="radio" name="cardTypeCode"
                                            id="visa" value="visa" />
                                </li>
                                <li class="master disabled">
                                    <input type="radio" name="cardTypeCode"
                                            id="mastercard" value="master" />
                                </li>
                                <li class="diners disabled">
                                    <input type="radio" name="cardTypeCode"
                                            id="diners" value="diners" />
                                </li>
                                <li class="amex disabled">
                                    <input type="radio" name="cardTypeCode"
                                            id="american" value="amex">
                                </li>
                                <li class="elo disabled">
                                    <input type="radio" name="cardTypeCode"
                                            id="elo" value="elo" />
                                </li>
                            </ul>
                            <small>
                                <spring:theme code="checkout.bandeiraCartao" text="Bandeira Cartao Selecionada Auto"/>
                            </small>
                        </div>
                        <div class="f-row">
                            <formElement:formInputBox idKey="nameOnCard"
                                labelKey="payment.nameOnCard" path="nameOnCard"
                                inputCSS="text required-payment required-letters"
                                mandatory="false" />
                        </div>
                        <div class="f-row">
                            <label for="cartao-vencimento-mes">
                                <spring:theme code="payment.expiryDate" />
                            </label>
                            <formElement:formSelectBox idKey="cartao-vencimento-mes"
                                path="expiryMonth" mandatory="false"
                                skipBlank="true" skipBlankMessageKey=""
                                items="${months}"
                                selectCSSClass="required-payment" />
                            <formElement:formSelectBox idKey="ExpiryYear"
                                path="expiryYear" mandatory="false"
                                skipBlank="true" skipBlankMessageKey="" 
                                items="${expiryYears}" 
                                selectCSSClass="required-payment"/>
                        </div>
                        <div class="f-row">
                            <label class="control-label" for="cv2Number">
                                <spring:theme code="payment.cv2Number" />
                                <span class="mandatory">*</span>
                                <span class="skip"></span>
                            </label>
                            <div class="f-row-70">
                                <input id="cv2Number" name="cv2Number"
                                        class="text required-payment required-numbers"
                                        type="text" maxlength="5" autocomplete="off" />
                            </div>
                            <div class="f-row-30">
                                <a href="#" class="hover-tooltip">
                                    <spring:theme code="checkout.oQueIsso" text="O que e isso"/>
                                    <div class="tooltip information-content securityCodeInfo">
                                        <h3><spring:theme code="checkout.toolTip.CodSeguranca" text="Codigo de seguranca"/></h3>
                                        <ul class="code-information">
                                            <li>
                                                <h3>Visa, Mastercard,
                                                    DinersClub, Hipercard
                                                </h3>
                                                <img src="/store/_ui/desktop/theme-hering/images/card-safe-code.png"
                                                        alt="">
                                                <p><spring:theme code="checkout.tooltip.tresDigitos" text="Encontra no verso 3 digitos"/></p>
                                            </li>
                                            <li class="amex">
                                                <h3>American Express</h3>
                                                <img src="/store/_ui/desktop/theme-hering/images/card-safe-code-american-express.png"
                                                        alt="">
                                                <p><spring:theme code="checkout.tooltip.quatroDigitos" text="Encontra na frente 4 digitos"/></p>
                                            </li>
                                        </ul>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </section>
                </c:if>
                <c:if test="${paymentMode.code == 'Boleto'}">
                    <section id="payment-billet">
                        <header>
                            <label>
                                <input type="radio"
                                        name="paymentMode"
                                        value="${paymentMode.code}"
                                        ${checked}
                                        id="${paymentMode.name}" />
                                ${paymentMode.name}
                            </label>
                        </header>
                        <p>
                            <i class="fa fa-barcode"></i>
                            <spring:theme code="checkout.popUpBoleto" text="Texto PopUp para Boleto"/>
                        </p>
                        <p><spring:theme code="checkout.infoEntrega" text="Texto Informacao Entrega"/></p>
                    </section>
                </c:if>
                <c:if test="${paymentMode.code == 'Voucher'}">
                    <section id="payment-gift-card">
                        <header>
                            <label>
                                ${paymentMode.name}
                            </label>
                        </header>
	                	<div class="f-row div-vale-credito">
                            <div class="vale-credito-not-applied"
                                    style="${hasAppliedValeCredito ? 'display: none' : ''}">
                                <formElement:formInputBox idKey="voucher"
                                        labelKey="" path="voucher"
                                        inputCSS="text required-voucher"
                                        mandatory="false" />
    	                        <div>
    			                	<input type="hidden" name="applyVcUrl"
                                            value="${request.contextPath}/checkout/single/apply-vc/" />
    	                        	<a href="#" class="btn applyVC btn-voucher">
                                        <spring:theme code="checkout.single.payment.redeemValeCredito"/>
                                    </a>
    	                        </div>
                            </div>
                            <div class="vale-credito-applied"
                                    style="${hasAppliedValeCredito ? '' : 'display: none'}">
                                <input type="hidden" name="applyVcUrl"
                                        value="${request.contextPath}/checkout/single/remove-vc/" />
                                <input type="hidden" name="vcCode" value="${appliedValeCreditoCode}"/>
    	                    	<a href="#" class="btn applyVC btn-voucher">
                                    <spring:theme code="checkout.single.payment.releaseValeCredito"/>
                                </a>
                            </div>
                        </div>
                    </section>
                </c:if>
            </div>
        </c:forEach>
<%--     </form:form> --%>
</c:if>