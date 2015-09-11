<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="deliveryAddress" required="true" type="de.hybris.platform.commercefacades.user.data.AddressData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="single-checkout" tagdir="/WEB-INF/tags/desktop/checkout/single" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="/WEB-INF/tld/ycommercetags.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:url value="/checkout/single/summary/getPaymentDetailsForm.json" var="getPaymentDetailsFormUrl"/>


<script type="text/javascript">
/*<![CDATA[*/
	$(document).ready(function() {
		bindAddPaymentMethodButton();		
	});

	
	function bindAddPaymentMethodButton()
	{
		$('div.checkout_summary_flow_c .change_payment_method_button').click(function() {
			
			var options = {
					url: '${getPaymentDetailsFormUrl}',
					data: {createUpdateStatus: ''},
					target: '#popup_checkout_add_payment_method',
					type: 'GET',
					success: function(data) {
						
						bindCreateUpdatePaymentDetailsForm();

						// Show the payment method popup
						$.colorbox({
							inline:true,
							href:"#popup_checkout_add_payment_method",
							height: false,
							overlayClose: false,
							onComplete: function() {
							    ACC.common.refreshScreenReaderBuffer();
							},
							onClosed: function() {
								ACC.common.refreshScreenReaderBuffer();
							}
						});
					},
					error: function(xht, textStatus, ex) {
						alert("Failed to get payment details. Error details [" + xht + ", " + textStatus + ", " + ex + "]");
					}
				};

			$(this).ajaxSubmit(options);

			return false;
		});
	}
	
	
	
	
	function bindCreateUpdatePaymentDetailsForm()
	{
	
		$('.create_update_payment_form').each(function () {
			var options = {
				type: 'POST',
				beforeSubmit: function() {
					$('#popup_checkout_add_payment_method').block({ message: "<img src='${commonResourcePath}/images/spinner.gif' />" });
				},
				success: function(data) {
					$('#popup_checkout_add_payment_method').html(data);
					var status = $('.create_update_address_id').attr('status');
					if(status != null && "success" == status.toLowerCase())
					{
						getCheckoutCartDataAndRefreshPage();					
						parent.$.colorbox.close();
					}
					 else
					{
						bindCreateUpdatePaymentDetailsForm();
					} 
				},
				error: function(xht, textStatus, ex) {
					alert("Failed to create/update payment details. Error details [" + xht + ", " + textStatus + ", " + ex + "]");
				},
				complete: function () {
					$('#popup_checkout_add_payment_method').unblock();
				}
			};

			$(this).ajaxForm(options);

		});
	}
	

	function refreshPaymentDetailsSection(data)
	{
		window.location.reload();		
		bindAddPaymentMethodButton();		 
	}
	
	
/*]]>*/
</script>


<c:set value="${not empty deliveryAddress}" var="deliveryAddressOk"/>
<c:set value="${not empty klarnaPaymentMode}" var="klarnaPaymentModeOk"/>

<div class="checkout_summary_flow_c ${klarnaPaymentModeOk ? 'complete' : ''}" id="checkout_summary_payment_div">
	<div class="item_container_holder">
		<ycommerce:testId code="checkout_paymentDetails_text">
			<div class="title_holder">
				<div class="title">
					<div class="title-top">
						<span></span>
					</div>
				</div>
				<h2><spring:theme code="checkout.summary.paymentMethod.header" htmlEscape="false"/><span></span></h2>
			</div>

			<div class="item_container">
				<div class="left">
					<ul>					
					<c:choose>
						<c:when test="${klarnaPaymentModeOk}">	
						<c:if test="${klarnaPaymentMode.code == 'klarnaInvoice'}">
							<li class="paymentmode-description">${fn:escapeXml(klarnaPaymentMode.name)} - <spring:theme code="checkout.summary.selected.paymentMethod.description.klarnaInvoice" arguments="${formattedKlarnaInvoiceFee}" argumentSeparator="#~/@!£$%^" /></li>
						</c:if>			
						<c:if test="${klarnaPaymentMode.code != 'klarnaInvoice'}">			
							<li class="paymentmode-description">${fn:escapeXml(klarnaPaymentMode.name)} - <spring:theme code="checkout.summary.selected.paymentMethod.description.${klarnaPaymentMode.code}"/></li>
						</c:if>							
						</c:when>
						<c:otherwise>
							<li><spring:theme code="checkout.summary.paymentMethod.paymentDetails.noneSelected"/></li>
						</c:otherwise>
					</c:choose>
					</ul>
				</div>
				<div class="right">
				<ul>
					<c:if test="${klarnaPaymentModeOk}">
						<li><spring:theme code="checkout.summary.paymentMethod.klarna.paymentadr.equals.deliveryadr"/></li>						
					</c:if>
				</ul>
				</div>
			</div>
		</ycommerce:testId>
	</div>
	
	<c:if test="${deliveryAddressOk}">
		<ycommerce:testId code="checkout_changePayment_element">	
			<c:choose>
				<c:when test="${klarnaPaymentModeOk}">
					<a href="#" class="edit_complete change_payment_method_button"><spring:theme code="checkout.summary.paymentMethod.editPaymentMethod"/></a>
				</c:when>
				<c:otherwise>
					<button class="form change_payment_method_button"><spring:theme code="checkout.summary.paymentMethod.editPaymentMethod"/></button>
				</c:otherwise>
			</c:choose>		
		</ycommerce:testId>
	</c:if>
</div>


<div style="display:none">
	<div class="item_container_holder edit_payment_details_modal" id="popup_checkout_add_payment_method">
		<single-checkout:paymentDetailsForm/>
	</div>
</div>

