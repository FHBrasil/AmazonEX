if(!ACC)
	ACC = {};
if(!ACC.amazon)
	ACC.amazon = {};

//$(document).ready(function() {
	$.getScript(ACC.addons.amazonpaymentaddon.amazonWidgetUrl)
	.done(function(script, textStatus){
		window.onAmazonLoginReady = function() {
			amazon.Login.setClientId(ACC.addons.amazonpaymentaddon.clientId);
		};
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
			new OffAmazonPayments.Widgets.AddressBook({
				sellerId: ACC.addons.amazonpaymentaddon.sellerId,
				// amazonOrderReferenceId: amazonOrderReferenceId,
				onOrderReferenceCreate: function(orderReference) {
					ACC.amazon.amazonOrderReferenceId = orderReference.getAmazonOrderReferenceId();
					$('input[name=amazonOrderReferenceId]').val(orderReference.getAmazonOrderReferenceId())
					alert(ACC.amazon.amazonOrderReferenceId);
				},
				onAddressSelect: function(orderReference) {
					$.ajax({
						url: ACC.config.contextPath + '/checkout/amazon/select-delivery-address',
						type: 'post',
						data: { amazonOrderReferenceId: ACC.amazon.amazonOrderReferenceId }
					}).success(function(response){
						// do something with the response...
						// like display shipping or taxes to the customer
					})
					.error(function (xht, textStatus, ex) {
						// alert("Ajax call failed while trying to set delivery mode. Error details [" + xht + ", " + textStatus + ", " + ex + "]");
					})
					.complete(function () {
						// alert("complete");
					});
					// Replace the following code with the action that you want to perform 
					// after the address is selected.
					// The amazonOrderReferenceId can be used to retrieve 
					// the address details by calling the GetOrderReferenceDetails
					// operation. If rendering the AddressBook and Wallet widgets on the
					// same page, you should wait for this event before you render the
					// Wallet widget for the first time.
					// The Wallet widget will re-render itself on all subsequent 
					// onAddressSelect events, without any action from you. It is not 
					// recommended that you explicitly refresh it.
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
			new OffAmazonPayments.Widgets.Wallet({
				sellerId: ACC.addons.amazonpaymentaddon.sellerId,
				onPaymentSelect: function(orderReference) {
					// Replace this code with the action that you want to perform
					// after the payment method is selected.
				},
				design: {
					designMode: 'responsive'
				},
				onError: function(error) {
					// your error handling code
				}
			}).bind("walletWidgetDiv");
		}
	})
	.fail(function( jqxhr, settings, exception ) {
		//alert( "Triggered ajaxError handler." );
	});
//});