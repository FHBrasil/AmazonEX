if(!ACC)
	ACC = {};
if(!ACC.amazon)
	ACC.amazon = {};

getUrlParam = function(name){
	var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
	if (results==null){
		return null;
	} else {
		return results[1] || 0;
	}
}

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
	amazon.Login.setClientId(ACC.addons.amazonaddon.clientId);
};
function checkEnableCheckout() {
	var allOk = true;
	for(var condition in ACC.amazon.enablePlaceOrder) {
		allOk = allOk && ACC.amazon.enablePlaceOrder[condition];
	}
	var disable = !allOk;
	window.$('#amazonPlaceOrder').attr('disabled', disable);
};
function retrieveGuestInformation() {
	amazon.Login.retrieveProfile(getUrlParam('access_token'), function(response) {
		if(response.success) {
			var form = $('#amazonGuestInformation');
			if(form.length) {
				form.find('input[name="amazonOrderReferenceId"]').val(ACC.amazon.amazonOrderReferenceId);
				form.find('input[name="amazonGuestId"]').val(response.profile.CustomerId);
				form.find('input[name="amazonGuestName"]').val(response.profile.Name);
				form.find('input[name="amazonGuestEmail"]').val(response.profile.PrimaryEmail);
				postGuestInformation();
			}
		}
	});
}
function postGuestInformation() {
	var form = $('#amazonGuestInformation');
	if(form.length) {
		$.ajax({
			type: "POST",
			url: form.attr('action'),
			data: form.serialize()
		});
	}
}
ACC.amazon.$ = $;
$.getScript(ACC.addons.amazonaddon.amazonWidgetsUrl)
	.done(function(script, textStatus){
		if(ACC.amazon.$('#AmazonPayButton').length) {
			var authRequest;
			var returnUrl = ACC.config.contextPath + '/checkout/amazon';
			var buttonColor = ACC.amazon.$('#AmazonPayButton').data("color");
			if(!buttonColor) buttonColor = "Gold";
			var buttonSize = ACC.amazon.$('#AmazonPayButton').data("size");
			if(!buttonSize) buttonSize = "medium";
			OffAmazonPayments.Button("AmazonPayButton", ACC.addons.amazonaddon.sellerId, {
				type:  "PwA",
				color: buttonColor,
				size:  buttonSize,

				authorization: function() {
					loginOptions =
						{scope: "profile payments:widget", popup: "true"};
					authRequest = amazon.Login.authorize (loginOptions, returnUrl);
				},
				onError: function(error) {
					// your error handling code
				}
			});
		}
		if(ACC.amazon.$('#addressBookWidgetDiv').length) {
			ACC.amazon.enablePlaceOrder.addressSelected = false;
			new OffAmazonPayments.Widgets.AddressBook({
				sellerId: ACC.addons.amazonaddon.sellerId,
				// amazonOrderReferenceId: amazonOrderReferenceId,
				onOrderReferenceCreate: function(orderReference) {
					ACC.amazon.amazonOrderReferenceId = orderReference.getAmazonOrderReferenceId();
					ACC.amazon.$('input[name=amazonOrderReferenceId]').val(orderReference.getAmazonOrderReferenceId())
					retrieveGuestInformation();
				},
				onAddressSelect: function(orderReference) {
					ACC.amazon.$.ajax({
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
		if(ACC.amazon.$('#walletWidgetDiv').length) {
			ACC.amazon.enablePlaceOrder.paymentSelected = false;
			new OffAmazonPayments.Widgets.Wallet({
				sellerId: ACC.addons.amazonaddon.sellerId,
				onPaymentSelect: function(orderReference) {
					ACC.amazon.$.ajax({
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
		if(ACC.amazon.$('#confirmChargeOnOrder').length) {
			ACC.amazon.enablePlaceOrder.confirmChargeOnOrder = ACC.amazon.$('#confirmChargeOnOrder')[0].checked;
			ACC.amazon.$('#confirmChargeOnOrder').change(function() {
				ACC.amazon.enablePlaceOrder.confirmChargeOnOrder = this.checked;
				checkEnableCheckout();
			});
		}
	})
	.fail(function( jqxhr, settings, exception ) {
		alert( "Triggered ajaxError handler." );
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