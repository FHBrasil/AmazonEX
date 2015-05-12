ACC.checkout = {
		
	spinner : $("<img id='taxesEstimateSpinner' src='"
			+ ACC.config.commonResourcePath + "/images/spinner.gif' />"),

	bindAll : function() {
		this.bindCheckO();
	},

	bindCheckO : function() {
		var cartEntriesError = false;

		// Alternative checkout flows options
		$('.doFlowSelectedChange').change(function() {
			if ('multistep-pci' == $('#selectAltCheckoutFlow').attr('value')) {
				$('#selectPciOption').css('display', '');
			} else {
				$('#selectPciOption').css('display', 'none');

			}
		});

		// Alternative checkout flows version of the doCheckout method to handle
		// the checkout buttons on the cart page.
		$('.doCheckoutBut').click(
				function() {
					var checkoutUrl = $(this).data("checkoutUrl");

					cartEntriesError = ACC.pickupinstore
							.validatePickupinStoreCartEntires();
					if (!cartEntriesError) {
						var expressCheckoutObject = $('.doExpressCheckout');
						if (expressCheckoutObject.is(":checked")) {
							window.location = expressCheckoutObject
									.data("expressCheckoutUrl");
						} else {
							var flow = $('#selectAltCheckoutFlow')
									.attr('value');
							if ('' == flow) {
								// No alternate flow specified, fallback to
								// default behaviour
								window.location = checkoutUrl;
							} else {
								// Fix multistep-pci flow
								if ('multistep-pci' == flow) {
									flow = 'multistep';
								}
								var pci = $('#selectPciOption').attr('value');

								// Build up the redirect URL
								var redirectUrl = checkoutUrl
										+ '/select-flow?flow=' + flow + '&pci='
										+ pci;
								window.location = redirectUrl;
							}
						}
					}
					return false;
				});

		$('#estimateTaxesButton')
				.click(
						function() {
							$('#zipCodewrapperDiv').removeClass(
									"form_field_error");
							$('#countryWrapperDiv').removeClass(
									"form_field_error");

							var countryIso = $('#countryIso').val();
							if (countryIso === "") {
								$('#countryWrapperDiv').addClass(
										"form_field_error");
							}
							var zipCode = $('#zipCode').val();
							if (zipCode === "") {
								$('#zipCodewrapperDiv').addClass(
										"form_field_error");
							}

							if (zipCode !== "" && countryIso !== "") {
								$("#order_totals_container").append(
										ACC.checkout.spinner);
								$
										.getJSON(
												"cart/estimate",
												{
													zipCode : zipCode,
													isocode : countryIso
												},
												function(estimatedCartData) {
													$("#estimatedTotalTax")
															.text(
																	estimatedCartData.totalTax.formattedValue)
													$("#estimatedTotalPrice")
															.text(
																	estimatedCartData.totalPrice.formattedValue)
													$(".estimatedTotals")
															.show();
													$(".realTotals").hide();
													$("#taxesEstimateSpinner")
															.remove();

												});
							}
						});
	},

	scrollToEditAddressPanel : function() {
		if ($("#editAnchor").length) {
			$('html, body').animate({
				scrollTop : $("#editAnchor").offset().top
			});
		}
	},

	nextPerformance : function() {
		if ($(".orderConfirmationData").length
				&& ($(".userData").find("input.userCode").val() != '' && $(
						".userData").find("input.userCode").val() != 'anonymous')) {
			var productSkus = '';
			var iterations = 0;
			$('.orderConfirmationProductDetailData').find('input.productSku')
					.each(
							function() {
								productSkus += $(this).val();
								productSkus += ++iterations < $(
										'.orderConfirmationProductDetailData')
										.find('input.productSku').length ? ','
										: '';
							});
		}
		var orderId = $('.orderConfirmationData').find('input.orderID').val();
		var amount = $('.orderConfirmationData').find('.orderTotalPrice').val();
		var assignedToNextPerf = 0;
		$('div.continueShopping').after(
				"<script type='text/javascript' "
						+ "src='https://nxtck.com/act.php?tag=40874" + "&pid="
						+ productSkus + "&id=" + orderId + "&mt=" + amount
						+ "&tvalid=" + assignedToNextPerf + "'><" + "/script>");
	},

	
	/*!enableOrDisableAllSteps : function() {
		var selectedDeliveryAddressId = 
				$("input[type=hidden].selectedDeliveryAddressCodeId").val();
		var selectedDeliveryMethodCode = 
				$("input[type=hidden].selectedDeliveryMethodCode").val();
		if(selectedDeliveryAddressId == '') { 
			$('ul.delivery_methods_list *').prop('disabled', 'disabled');
		} else {
			ACC.checkout.enableStepTwo();
		}
		if(selectedDeliveryMethodCode == '') {
			$('span#heringCustomAddPaymentMethod *').prop('disabled',
					'disabled');
		} else {
			ACC.checkout.enableStepThree();
		}
	},*/

	
	enableStepTwo : function() {
		$('ul.delivery_methods_list *').removeAttr('disabled');
	},
	
	
	enableStepThree: function() {
		$('span#heringCustomAddPaymentMethod *').removeAttr('disabled');	
	},
	
	
	checkAndSaveVoucherCode : function() {
		$('#voucherPayment input[type=text]#voucher');
	}
	
};

$(document).ready(function() {
	var contextPath = $('input[type=hidden].contextPath').val();
	ACC.checkout.scrollToEditAddressPanel();
	ACC.checkout.bindAll();
	ACC.checkout.nextPerformance();
	//ACC.checkout.enableOrDisableAllSteps();
	ACC.checkout.checkAndSaveVoucherCode();
	$('.addressButtonUseAnotherAddress').click(function() {
		$('#savedAddressListHolder').hide();
		$('.addressList').show();
	});
	$('button[type=button].buttonAddNewAddress').click(function() {
		$('div.novoEndereco').show();
	});
	$('input[type=radio][name=delivery_method]').change(function() {
		var deliveryMethods = $('ul.delivery_methods_list');
		$.ajax({
			async : true,
			url : contextPath + '/checkout/multi/select-delivery-method',
			data : {
				selectedDeliveryMethodCode : $(this).val()
			},
			dataType : 'json',
			type : 'GET',
			success : function(data) {
				if (data) {
					ACC.checkout.enableStepThree();
				}
			}
		});
	});
	$('button[type=button].buttonBillingAddressFormSendForm').click(function() {
		$.ajax({
			async : true,
			url : contextPath + '/checkout/multi/add-billing-address',
			type : 'POST',
			dataType : 'json',
			data : $('form#heringBillingAddressForm').serialize(),
			success : function(data) {
				if(data.valueOf() == 'true') {
					$('div#newBillingAddressFields').hide();
					GlobalMessages.infoMessage('Endereço de cobrança cadastrado'
							+ ' com sucesso', true);
				} else {
					GlobalMessages.errorMessage('Erro ao salvar endereço de '
							+ 'cobrança. Verifique se todos os campos foram '
							+ 'preenchidos', false);
				}
			}
		});
	});
	$('input[type=text][name=voucher]').blur(function() {
		$.ajax({
			async : true,
			url : contextPath + '/checkout/multi/is-voucher-valid',
			type : 'GET',
			dataType : 'json',
			data : {
				voucherCode : $(this).val()
			},
			success : function(data) {
				if(data) {
					GlobalMessages.infoMessage('Cupom válido', false);
				} else {
					GlobalMessages.errorMessage('Cupom inválido', true);
				}
			}
		});
	});
	$('input[type=checkbox]#differentAddress').prop('checked', 'checked');
	$('input[type=checkbox]#differentAddress').click(function() {
		if($(this).is(':checked')) {
			$('div#newBillingAddressFields').hide();
		} else {
			$('div#newBillingAddressFields').show();
		}
	});
	$('button[type=button].confirmPurchase').click(function() {
		
		var formPayment = $('form#paymentDetailsForm').serialize();
		var formBilling = $('form#heringBillingAddressForm').serializeArray();
		for(i in formBilling) {
		    formPayment += '&billingAddress.' + formBilling[i].name + '=' + formBilling[i].value;
		}
		
		var url = contextPath + '/checkout/multi/single/placeOrder';
		var fullURL = url + '?' + formPayment;
		window.location = fullURL;
	});
});
