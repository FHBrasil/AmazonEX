if(!ACC)
	ACC = {};
if(!ACC.amazon)
	ACC.amazon = {};

ACC.amazon.enablePlaceOrder = {};

if(window.onAmazonLoginReady) {
	var parent = window.onAmazonLoginReady;
	window.onAmazonLoginReady = function() {
		parent();
		paymentLoginHandler();
	}
} else {
	window.onAmazonLoginReady = paymentLoginReadyHandler;
}
function paymentLoginReadyHandler() {
	amazon.Login.setClientId(ACC.addons.amazonpaymentaddon.clientId);
	ACC.amazon.enablePlaceOrder.loginReady = true;
	checkEnableCheckout();
};
function checkEnableCheckout() {
	var allOk = true;
	for(var condition in ACC.amazon.enablePlaceOrder) {
		allOk = allOk && ACC.amazon.enablePlaceOrder[condition];
	}
	var disable = !allOk;
	window.$('#amazonPlaceOrder').attr('disabled', disable);
};
function postGuestInformation() {
	var form = $('#amazonGuestInformation');
	if(form.length) {
		form.find('input[name="amazonOrderReferenceId"]').val(ACC.amazon.amazonOrderReferenceId);
		$.ajax({
			type: "POST",
			url: form.attr('action'),
			data: form.serialize()
		});
	}
}
$.getScript(ACC.addons.amazonpaymentaddon.amazonWidgetUrl)
	.done(function(script, textStatus){
		ACC.amazon.enablePlaceOrder.loginReady = false;
		if($('#AmazonPayButton').length) {
			var authRequest;
			var returnUrl = ACC.config.contextPath + '/checkout/amazon';
			OffAmazonPayments.Button("AmazonPayButton", ACC.addons.amazonpaymentaddon.sellerId, {
				type:  "PwA",
				color: "Gold",
				size:  "medium",
				language: "en-GB",

				authorization: function() {
					loginOptions =
						{scope: "payments:widget", popup: "true"};
					authRequest = amazon.Login.authorize (loginOptions, returnUrl);
				},
				onError: function(error) {
					// your error handling code
				}
			});
		}
		if($('#addressBookWidgetDiv').length) {
			ACC.amazon.enablePlaceOrder.addressSelected = false;
			new OffAmazonPayments.Widgets.AddressBook({
				sellerId: ACC.addons.amazonpaymentaddon.sellerId,
				// amazonOrderReferenceId: amazonOrderReferenceId,
				onOrderReferenceCreate: function(orderReference) {
					ACC.amazon.amazonOrderReferenceId = orderReference.getAmazonOrderReferenceId();
					$('input[name=amazonOrderReferenceId]').val(orderReference.getAmazonOrderReferenceId())
					postGuestInformation();
				},
				onAddressSelect: function(orderReference) {
					$.ajax({
						url: ACC.config.contextPath + '/checkout/amazon/select-delivery-address',
						type: 'post',
						data: { amazonOrderReferenceId: ACC.amazon.amazonOrderReferenceId
							, CSRFToken: ACC.config.CSRFToken },
						success: function(response){
							if(response && response.showMessage) {
								ACC.amazon.showToaster(response.showMessage);
							}
						},
						error: function (xht, textStatus, ex) {
							// alert("Ajax call failed while trying to set delivery mode. Error details [" + xht + ", " + textStatus + ", " + ex + "]");
						},
						complete: function () {
							// alert("complete");
						}
					});
					ACC.amazon.enablePlaceOrder.addressSelected = true;
					checkEnableCheckout();
				},
				design: {
					designMode: 'responsive'
				},
				onError: function(error) {
					// your error handling code
				}
			}).bind("addressBookWidgetDiv");
		}
		if($('#walletWidgetDiv').length) {
			ACC.amazon.enablePlaceOrder.paymentSelected = false;
			new OffAmazonPayments.Widgets.Wallet({
				sellerId: ACC.addons.amazonpaymentaddon.sellerId,
				onPaymentSelect: function(orderReference) {
					$.ajax({
						url: ACC.config.contextPath + '/checkout/amazon/select-payment-method',
						type: 'post',
						data: { amazonOrderReferenceId: ACC.amazon.amazonOrderReferenceId
							, CSRFToken: ACC.config.CSRFToken },
						success: function(response){
							if(response && response.showMessage) {
								ACC.amazon.showToaster(response.showMessage);
							}
						},
						error: function (xht, textStatus, ex) {
						},
						complete: function () {
						}
					});
					ACC.amazon.enablePlaceOrder.paymentSelected = true;
					checkEnableCheckout();
				},
				design: {
					designMode: 'responsive'
				},
				onError: function(error) {
					// your error handling code
				}
			}).bind("walletWidgetDiv");
		}
		if($('#confirmChargeOnOrder').length) {
			ACC.amazon.enablePlaceOrder.confirmChargeOnOrder = $('#confirmChargeOnOrder')[0].checked;
			$('#confirmChargeOnOrder').change(function() {
				ACC.amazon.enablePlaceOrder.confirmChargeOnOrder = this.checked;
				checkEnableCheckout();
			});
		}
	})
	.fail(function( jqxhr, settings, exception ) {
		//alert( "Triggered ajaxError handler." );
	});

ACC.amazon.toasterActive = undefined;
ACC.amazon.showToaster = function(msg) {
	var toaster = $("#toaster");
	if(!toaster[0]) {
		$("body").prepend('<div id="toaster"><div class="content"></div></div>');
		toaster = $("#toaster");
	}
	clearTimeout(ACC.amazon.toasterActive);
	toaster.find(".content").text(msg);
	toaster.addClass("active");
	ACC.amazon.toasterActive = setTimeout(function() { toaster.removeClass("active") }, 4000);
};