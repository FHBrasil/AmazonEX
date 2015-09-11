<%@ taglib prefix="spring"		uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" 			uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ycommerce"	uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" 		uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="format" 		tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="form" 		uri="http://www.springframework.org/tags/form"%>

<div class="col-sm-4">
	<h2>
		<a href="https://www.trustedshops.de/shop/certificate.php?shop_id=XA2EF864014A142CF9EDC2483FE556551" class="trust150706" target="_blank">
			<spring:theme code="text.fliegercommerce.texto182" />
		</a>
	</h2>
	<p><spring:theme code="text.fliegercommerce.texto183" /></p>
	<form id="formTShops" name="formTShops" method="post" action="https://www.trustedshops.com/shop/protection.php" target="_blank">
		<input type="hidden" name="_charset_" />
		<input name="shop_id" type="hidden" value="XA2EF864014A142CF9EDC2483FE556551" />
		<input name="email" type="hidden" value="${email}" />
		<input name="amount" type="hidden" value="${orderData.totalPrice.value}" />
		<input name="curr" type="hidden" value='<spring:theme code="orderConfirmation.trusted.EUR"/>' />
		<c:choose>
			<c:when test="${orderData.paymentMode.code == 'Advance'}">
				<input name="payment" type="hidden" value='<spring:theme code="orderConfirmation.trusted.paymentMode.advance"/>' />
			</c:when>
			<c:when test="${orderData.paymentMode.code == 'Creditcard'}">
				<input name="payment" type="hidden" value='<spring:theme code="orderConfirmation.trusted.paymentMode.creditcard"/>' />
			</c:when>
			<c:when test="${orderData.paymentMode.code == 'debit'}">
				<input name="payment" type="hidden" value='<spring:theme code="orderConfirmation.trusted.paymentMode.debit"/>' />
			</c:when>
			<c:otherwise>
				<input name="payment" type="hidden" value="Sonstige" />
			</c:otherwise>
		</c:choose>
	  	<input name="KDNR" type="hidden" value="${orderData.user.uid}" />
	  	<input name="ORDERNR" type="hidden" value="${orderData.code}" />
	  	<button type="submit" class="btn btn-default"><spring:theme code="text.fliegercommerce.texto184" /></button>
	</form>
</div>