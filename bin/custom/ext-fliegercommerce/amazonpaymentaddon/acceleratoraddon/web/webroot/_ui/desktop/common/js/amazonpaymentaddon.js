if(!ACC)
	ACC = {};
if(!ACC.amazon)
	ACC.amazon = {};

getUrlParam = function(name){
	var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
	if (results==null){
		return null;
	} else {
		return decodeURI(results[1]) || 0;
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
	$('.confirm-purchaseAmazon').attr('disabled', disable);
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
if (ACC.addons.amazonaddon.isAmazonEnabled == 'true') {
	if (ACC.addons.amazonaddon.isAmazonPayEnabled == 'true') {
		
		// prevent amazon scripts from overwriting current jquery version
		ACC.addons.amazonaddon.$ = $;
		$.ajaxSetup({
			complete: function() {
				if (window.$ !== ACC.addons.amazonaddon.$) {
					window.$ = window.jquery = window.jQuery = ACC.addons.amazonaddon.$;
				}
			}
		});
		
		// loads amazon scripts
		$.getScript(ACC.addons.amazonaddon.amazonWidgetsUrl).done(function(script, textStatus){
				if($('#AmazonPayButton').length) {
					var authRequest;
					var returnUrl = ACC.config.contextPath + '/checkout/amazon';
					var buttonColor = ACC.addons.amazonaddon.payButtonColor;
					if(!buttonColor) buttonColor = "Gold";
					var buttonSize = ACC.addons.amazonaddon.payButtonSize;
					if(!buttonSize) buttonSize = "medium";
					OffAmazonPayments.Button("AmazonPayButton", ACC.addons.amazonaddon.sellerId, {
						type:  "PwA",
						color: buttonColor,
						size:  buttonSize,
		
						authorization: function() {
							loginOptions =
								{scope: "profile payments:widget payments:shipping_address payments:billing_address", popup: "true"};
							authRequest = amazon.Login.authorize (loginOptions, returnUrl);
						},
						onError: function(error) {
							// your error handling code
						}
					});
				}
				if (ACC.addons.amazonaddon.isHiddenButtonsMode == 'true') {
					$("#AmazonPayButton").hide();
				}
				if($('#addressBookWidgetDiv').length) {
					ACC.amazon.enablePlaceOrder.addressSelected = false;
					new OffAmazonPayments.Widgets.AddressBook({
						sellerId: ACC.addons.amazonaddon.sellerId,
						// amazonOrderReferenceId: amazonOrderReferenceId,
						onOrderReferenceCreate: function(orderReference) {
							ACC.amazon.amazonOrderReferenceId = orderReference.getAmazonOrderReferenceId();
							$('input[name=amazonOrderReferenceId]').val(orderReference.getAmazonOrderReferenceId())
							retrieveGuestInformation();
						},
						onAddressSelect: function(orderReference) {
							$.ajax({
								url: ACC.config.contextPath + '/checkout/amazon/select-delivery-address',
								type: 'post',
								dataType: 'json',
								data: { amazonOrderReferenceId: ACC.amazon.amazonOrderReferenceId
									, access_token: getUrlParam('access_token')
									, CSRFToken: ACC.config.CSRFToken },
								success: function(response){
									if(response.success === 'true') {
										ACC.amazon.enablePlaceOrder.addressSelected = true;
										checkEnableCheckout();
										ACC.amazon.showToaster(response.showMessage);
										
										$("#deliveryCost", "#totalPrice").fadeOut();
										
										setTimeout(function(){
											$('#deliveryCost').html(response.deliveryCost);
											$('#totalPrice').html(response.totalPrice);
											$('#shipping-methods').html(response.deliveryMethodSelector);
											bindDeliveryMethodOnChange();
										}, 500);
										
										$("#deliveryCost", "#totalPrice").fadeIn();
									} 
									else{
										ACC.amazon.enablePlaceOrder.addressSelected = false;
										checkEnableCheckout();
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
						sellerId: ACC.addons.amazonaddon.sellerId,
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
				alert( "Triggered ajaxError handler." );
			});
	}	
}
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

/*! Sends payment form */
$('.confirm-purchaseAmazon').click(function() {
	$('form#amazonPlaceOrderForm').submit();
});

$('#change-account').click(function() {
	amazon.Login.logout();
	$('#AmazonPayButton img').click();
});

function bindDeliveryMethodOnChange() {
	unbindDeliveryMethodOnChange();
	$('section#shipping-methods').find('input[type=radio][name=delivery_method]').change(function() {
		var selectedDeliveryAddressCode = $(this).val();
		var url = ACC.config.contextPath + '/checkout/amazon/select-delivery-method';
		if(selectedDeliveryAddressCode) {
			$.ajax({
				async : true,
				url : url,
				type : 'POST',
				dataType : 'json',
				data : { selectedDeliveryMethod : selectedDeliveryAddressCode, 
						 CSRFToken: ACC.config.CSRFToken },
				success : function(data) {
					if(data && data.success === 'true') {
						$("#deliveryCost,#totalPrice").fadeOut();
						setTimeout(function(){
							$('#deliveryCost').html(data.deliveryCost);
							$('#totalPrice').html(data.totalPrice);
						}, 500);
						$('#deliveryCost,#totalPrice').fadeIn();
					}
					console.log('success');
				}, complete : function() {
					console.log('complete');
				}, error : function(error) {
					console.error(error.responseText);
				}
			});
		}
	});
}

function unbindDeliveryMethodOnChange() {
	$('section#shipping-methods').find('input[type=radio][name=delivery_method]').unbind('change');
}

function changeQuantityOrRemove(entryNumber, remove){
	var $form = $("form#updateCartForm" + entryNumber);
	var $inputNumber = $("#updateCartForm" + entryNumber + " input[type='number']");
	if(remove){
		$inputNumber.val(0);
	}
	var qtyActual = $inputNumber.val();
	$.ajax({
		url : $form.attr("action"),
		dataType : 'json',
		type : 'post',
		data : $form.serialize(),
		success : function(data){
			var messageReturn = $('<div id="updateMessageReturnSuccess"> <div class="alert positive"> <a href="#" class="close" data-dismiss="alert">×</a>'+ data.message +'</div> </div>');
			var typeMessage = "updateMessageReturnSuccess";
			var cartData = data.cartData;
			
			//Change quantity of the item			
			if(qtyActual > 0){			
				var entryTotalPrice;
				var entryQty;				
				
				for(var count = 0; count < cartData.entries.length; count++){
					if(cartData.entries[count].entryNumber == entryNumber){
						entryQty = cartData.entries[count].quantity;
						entryTotalPrice = cartData.entries[count].totalPrice.formattedValue;
					}
				}
				
				//If condition is true, item update success
				if(qtyActual == entryQty){
					$inputNumber.val(entryQty);
					$("#entryTotalPrice" + entryNumber).fadeOut();
					$("#deliveryCost").fadeOut();
					$("#subTotal").fadeOut();
					$("#totalPrice").fadeOut();
					setTimeout(function(){
						$("#entryTotalPrice" + entryNumber).html(entryTotalPrice);
						$('#deliveryCost').html(cartData.deliveryCost.formattedValue);
						$('#subTotal').html(cartData.subTotal.formattedValue);
						$('#totalPrice').html(cartData.totalPrice.formattedValue);
					}, 500);
					$("#entryTotalPrice" + entryNumber).fadeIn();
					$("#deliveryCost").fadeIn();
					$('#subTotal').fadeIn();
					$('#totalPrice').fadeIn();
				}
				else{
					//Else item not update
					$inputNumber.val(entryQty);
					messageReturn = $('<div id="updateMessageReturnFail"> <div class="alert negative"> <a href="#" class="close" data-dismiss="alert">×</a>'+ data.message +'</div> </div>');
					typeMessage = "updateMessageReturnFail";
				}
			}
			else{
				//Removed item of the cart
				if(cartData.totalItems > 0){
					$('ol.list150608 li#' + entryNumber).fadeOut();
					$("#deliveryCost").fadeOut();
					$("#subTotal").fadeOut();
					$("#totalPrice").fadeOut();
					setTimeout(function(){
						$('ol.list150608 li#' + entryNumber).remove();
						$('#deliveryCost').html(cartData.deliveryCost.formattedValue);
						$('#subTotal').html(cartData.subTotal.formattedValue);
						$('#totalPrice').html(cartData.totalPrice.formattedValue);
					}, 800);
					$("#deliveryCost").fadeIn();
					$('#subTotal').fadeIn();
					$('#totalPrice').fadeIn();
				}
				else{
					location.reload();
				}
			}
			
			$('#globalMessages').html(messageReturn);
			$('body,html').animate({scrollTop: 0}, 800);
			setTimeout(function(){
				$('#' + typeMessage).slideUp(250, function(){
					$(this.remove());
				});
			}, 5000);
			
		},
		error : function(data){
			//
		}
	});
	
	bindDeliveryMethodOnChange();
}