<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="cartData" required="true"
    type="de.hybris.platform.commercefacades.order.data.CartData"%>
<%@ attribute name="showTaxEstimate" required="false" type="java.lang.Boolean"%>
<%@ attribute name="showTax" required="false" type="java.lang.Boolean"%>
<%@ attribute name="showCalculateDeliveryComponent" required="false" type="java.lang.Boolean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
    <c:if test="${showCalculateDeliveryComponent and not empty cartData.deliveryAddress}">
        <c:set var="showCalculateDeliveryComponent" value="false" />
    </c:if>
</sec:authorize>
<div chaordic="middle"></div>
<section id="cart-bottom">
    <div class="container">
        <div class="left">
<%--        
		XXX removendo carrier, isso precisa virar addon     
		<form:form action="${request.contextPath}/cart/calculateDelivery" method="POST"
                class="calculate-freight" id="calculateFrete">
                <h3>
                    <spring:theme code="basket.page.totals.delivery.calculate" />
                </h3>
                <div>
                    <label>Insira seu CEP:</label> <input type="text" maxlength="5" id="cep-01"
                        name="cep_01" required>- <input type="text" maxlength="3"
                        id="cep-02" name="cep_02" required> <input type="hidden"
                        id="cepCode" name="postalCode" pattern="[0-9]{8}" maxlength="8"
                        style="width: 110px" />
                    <button type="submit" onclick="cepFunction()" class="btn-ok btn">ok</button>
                </div>
            </form:form> --%>
            <cart:cartVoucher />
        </div>
        <%-- RESUMO DO PEDIDO // --%>
        <div class="right">
            <ul>
                <li><a href="/"><i class="fa fa-angle-left"></i><spring:theme code="text.fliegercommerce.texto57"/></a></li>
                <li class="right"><spring:theme code="text.fliegercommerce.texto58"/></li>
            </ul>
            <div>
                <ul class="total-info">
                    <!-- -->
                    <c:if test="${cartData.totalDiscounts.value > 0}">
                        <tr class="savings">
                            <td><spring:theme code="basket.page.totals.savings" /></td>
                            <td><ycommerce:testId code="Order_Totals_Savings">
                                    <format:price priceData="${cartData.totalDiscounts}" />
                                </ycommerce:testId></td>
                        </tr>
                    </c:if>
                    <!-- -->
                    <li class="total"><strong><format:price
                                priceData="${cartData.totalPrice}" /></strong> <c:if
                            test="${not empty cartData.deliveryCost}">
								(<spring:theme code="text.fliegercommerce.texto59"/> 
								<format:price priceData="${cartData.deliveryCost}" displayFreeForZero="TRUE" />
                            <c:if test="${not empty cartData.deliveryMode}">
                                <br> ${cartData.deliveryMode.description}
								</c:if>)
							</c:if></li>
                    <c:if test="${not empty instalments}">
                        <li>${instalments}</li>
                    </c:if>
                </ul>
                <c:url value="/cart/checkout/" var="checkoutUrl" />
                <a href="${checkoutUrl}" class="btn-checkout btn"><spring:theme
                        code="checkout.checkout" /></a>
            </div>
        </div>
    </div>
    <div class="button-paypal-express-checkout">
        <c:url value="/paypal/checkout/hop/expressCheckoutShortcut" var="paypalExpressCheckoutUrl" />
        <a class="paypal-express-checkout-link" href="${paypalExpressCheckoutUrl}"
                data-paypal-button="true" data-paypal-id="${merchantId}">
        </a>
    	<script>
    		(function(d, s, id) {
    			var js, ref = d.getElementsByTagName(s)[0];
    			if (!d.getElementById(id)) {
    				js = d.createElement(s);
    				js.id = id;
    				js.async = true;
    				js.src = "//www.paypalobjects.com/js/external/paypal.v1.js";
    				ref.parentNode.insertBefore(js, ref);
    			}
    		}(document, "script", "paypal-js"));
    	</script>
    </div>
</section>
