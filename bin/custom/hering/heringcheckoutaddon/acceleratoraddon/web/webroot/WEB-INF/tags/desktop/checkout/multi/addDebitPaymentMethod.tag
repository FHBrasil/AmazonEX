<%@ attribute name="paymentMode" required="true"
	type="br.hering.facades.order.data.PaymentModeData"%>
<%@ attribute name="checked" required="true" type="java.lang.String"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>

<input type="radio" name="paymentMode" value="${paymentMode.code}" ${checked}
	id="${paymentMode.name}" />
<label for="${paymentMode.name}">${paymentMode.name} </label>
<div id="debitDetails" class="cardForm" style="display: none;">
	<formElement:formInputBox idKey="debitPayment.accountNumber"
		labelKey="debitPayment.accountNumber" path="debitCardForm.accountNumber"
		inputCSS="text" mandatory="false" />
	<div id="creditCardsPayment">
		<ul>
			<li><input type="radio" name="cardTypeCode" id="visa-electron"
				value="visa-electron" /> <label for="visa-electron">Visa Electron</label></li>
		</ul>
	</div>
	<formElement:formInputBox idKey="debitPayment.cardAccountHolderName"
		labelKey="debitPayment.cardAccountHolderName"
		path="debitCardForm.cardAccountHolderName" inputCSS="text"
		mandatory="false" />
	<formElement:formInputBox idKey="debitPayment.cardCvNumber"
		labelKey="debitPayment.cardCvNumber" path="debitCardForm.cardCvNumber"
		inputCSS="text" mandatory="false" />
	<formElement:formInputBox idKey="debitPayment.cardExpirationMonth"
		labelKey="debitPayment.cardExpirationMonth" path="debitCardForm.cardExpirationMonth"
		inputCSS="text" mandatory="false" />
	<formElement:formInputBox idKey="debitPayment.cardExpirationYear"
		labelKey="debitPayment.cardExpirationYear" path="debitCardForm.cardExpirationYear"
		inputCSS="text" mandatory="false" />
</div>
<%-- </form:form> --%>
