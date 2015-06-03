<%@ page trimDirectiveWhitespaces="true" contentType="application/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="single-checkout-hering"
	tagdir="/WEB-INF/tags/addons/heringcheckoutaddon/desktop/checkout/single"%>

{
    "subtotalsAmount" : {
        "productsAmount" : "<spring:escapeBody javaScriptEscape="true">
        						<dt><spring:theme code="text.fliegercommerce.texto76"/></dt>
        						<dd class="subtotal">
        							<strong><format:price priceData="${cartData.subTotal}"/></strong>
    							</dd>
    						</spring:escapeBody>"
      , "freightAmount" : "<spring:escapeBody javaScriptEscape="true">
								<dt class="freight" ${not empty cartData.deliveryCost ? '' : 'style=\"display:none;\"'}>
      								<spring:theme code="basket.page.totals.delivery"/>
      							</dt>
      							<dd>
									<c:if test="${not empty cartData.deliveryCost}">
										<c:choose>
				                            <c:when test="${isVoucherFreeShipping}">
			                                	<spring:theme code="order.free" text="FREE" />
			                            	</c:when>
			                            	<c:otherwise>
			                                	<format:price priceData="${cartData.deliveryCost}"
			                                    	    displayFreeForZero="TRUE" />
			                            	</c:otherwise>
			                        	</c:choose>
								   		<small>${cartData.deliveryMode.description}</small>
									</c:if>
  								</dd>
							</spring:escapeBody>"
      , "discountAmount" : "<spring:escapeBody javaScriptEscape="true">
      							<c:if test="${cartData.totalDiscounts.value > 0}">
      								<dt class="discount">
      									<spring:theme code="basket.page.totals.savings" />
  									</dt>
  									<dd>
  										<ycommerce:testId code="Order_Totals_Savings">
  											<format:price priceData="${cartData.totalDiscounts}" />
										</ycommerce:testId>
									</dd>
								</c:if>
							</spring:escapeBody>"
    }
  , "paymentTotalAmount" :"<spring:escapeBody javaScriptEscape="true">
  								<dl class="payment-info">
  									<dt class="method"><spring:theme code="text.fliegercommerce.texto75"/></dt>
  									<dd class="method">
  										<c:if test="${hasAppliedValeCredito}">
											<spring:theme code="payment.valeCredito"/>
  										</c:if>
									</dd>
  									<dt class="total"><spring:theme code="text.fliegercommerce.texto77"/></dt>
  									<dd class="total">
  										<strong><format:price priceData="${cartData.totalPrice}"/></strong>
									</dd>
								</dl>
							</spring:escapeBody>"
  , "instalmentCreditCard" : "<spring:escapeBody javaScriptEscape="true">
								  		<div class="f-row">
								          <formElement:formSelectBox idKey="numero-parcelas"
								              labelKey="payment.instalment" path="instalments"
								              mandatory="true" skipBlank="true" 
								              items="${instalments}" />
								      </div>
							      </spring:escapeBody>" 
  , "deliveryMethodSelector" : "<spring:escapeBody javaScriptEscape="true">
	  								<input type="hidden" class="selectedDeliveryMethodCode"
	  										value="${selectedDeliveryMethodId}"/>
	  								<c:forEach items="${deliveryMethods}" var="deliveryMethod">
	  									<single-checkout-hering:deliveryMethodDetails deliveryMethod="${deliveryMethod}"
	  											isSelected="${deliveryMethod.code eq selectedDeliveryMethodId}"/>
	  								</c:forEach>
  								</spring:escapeBody>"    
  , "success" : "${success}"
  , "message" : "${successMessage}"
  , "command" : "${command}"
  , "isVoucherAmountEqualsOrderAmount" : "${isVoucherAmountEqualsOrderAmount}"
}