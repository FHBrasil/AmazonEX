$(document).ready(function() {
	
	//toggle method payment
	$('.closePayments').click(function(){
		  $('.paycollapse.collapse.in').collapse('hide');
	});
	
	$('.closeNewAddress').click(function(){
		$('.addresscollapse.collapse.in').collapse('hide');
	});
	
	$(".btn-checkout").click(function(){
		blockTwoClickPaymentButton();
	});
	
	var contextPath = $('input[type=hidden].contextPath').val();
	
	// Fancybox
	$(".fancybox").fancybox({
		maxWidth: 800,
		padding: 40
	});
	
	$(".fancybox.order").fancybox({
		width: "auto",
		maxWidth: 800
	});

	/*! Form validation */
	hering.form.validateAll();

	// define a maior altura para todas as colunas
	resizeColumns();
	$(window).resize(function() { resizeColumns(); });

	// abre e fecha sections em responsivo
	/*$('.columns-wrapper section header h2').click(function() {
		if($(window).width() <= 1199) {
			$(this).parents('.columns-wrapper').find('.checkout-column').each(function() {
				$(this).find('.section-container').slideUp('slow');
			});
			$(this).parents('section').find('.section-container').slideDown();
		}
	});*/

	$("#selected-address .address-info .btn-outro-endereco").click(function(e) {
		e.preventDefault();
		$('#new-address').slideUp('slow');
		$("body").animate({scrollTop: $("#selected-address").offset().top - 100}, 'slow');
		$(this).parents('.address-info').fadeOut("medium",function() {
			$(this).siblings('.address-prompt').fadeIn("medium");
		});
	});

	$("#selected-address .address-prompt .btn-novo-endereco").click(function(e) {
		e.preventDefault();
		$('#new-address form')[0].reset();
		$('#new-address').slideDown('slow');
		$(this).parent().find('li').removeClass('active');
		$("body").animate({scrollTop: $("#new-address").offset().top - 50}, 'slow');
	});

	/*! Verifies empty addres (for user and/or guest checkout) */
	if($('input[type=hidden].e-code').val() === "") {
		$('#new-address').slideDown('slow');
		$(this).parent().find('li').removeClass('active');
		$("body").animate({scrollTop: $("#new-address").offset().top - 50}, 'slow');
	}
	
	
//	$('.btn-editar').click(function(e){ return;
//		e.preventDefault();
//		var selectedAddress = $('section#selected-address div.address-info');
//		$('#new-address form')[0].reset();
//		$('#new-address #new-addr-addressId').val($(selectedAddress).find('.e-code').text());
//		$('#new-address #new-addr-cep').val($(selectedAddress).find('.e-cep').text());
//		$('#new-address #new-addr-tipo').val($(selectedAddress).find('.e-tipo').text());
//		$('#new-address #new-addr-addr').val($(selectedAddress).find('.e-addr').text());
//		$('#new-address #new-addr-numero').val($(selectedAddress).find('.e-numero').text());
//		$('#new-address #new-addr-bairro').val($(selectedAddress).find('.e-bairro').text());
//		$('#new-address #new-addr-complemento').val($(selectedAddress).find('.e-complemento').text());
//		$('#new-address #new-addr-estado').val($(selectedAddress).find('.e-estado').text());
//		$('#new-address #new-addr-cidade').val($(selectedAddress).find('.e-cidade').text());
//		$('#new-address').slideDown('slow');
//		// mmuda o nome do botão de editar
//		// $('#new-address .btn-addr-cadastrar').html('Cadastrar e utilizar este endereço');
//	});
//
//	/*! Edits delivery address */
//	$('section#selected-address').find('div.address-info').find('div.btn-group')
//	.find('a.btn-editar').click(function() {
//		var url = contextPath + '/checkout/single/edit-delivery-address?';
//		var addressCodeParam = 'addressCode=' 
//				+ $('section#selected-address').find('div.address-info')
//					.find('input[type=hidden].e-code').val();
//		window.location = url + addressCodeParam;
//	});
	
	$('form#selectAddress').find('button.btn-primary').click(function(){		
		var cod = $('input:radio[name=chooseDeliveryAddress]:checked').val();
		var typeAddress = $('input:hidden[name=deliveryOrBilling]').val();
		var url = contextPath + '/checkout/single/select-' + typeAddress + '-address/' + cod;
		$('form#selectAddress').attr('action', url).submit();
	});
	
	$('.btn-group .btn-editar').click(function(){
		if($(this).hasClass('billing'))
			$('input:hidden[name=deliveryOrBilling]').attr("value", 'billing');
		else
			$('input:hidden[name=deliveryOrBilling]').attr("value", 'delivery');
	});
	
	/*! Removes delivery address */
	$('section#selected-address').find('div.address-info').find('div.btn-group')
	.find('a.btn-excluir').click(function() {
		var url = contextPath + '/checkout/single/remove-address?';
		var addressCodeParam = 'addressCode=' 
				+ $('section#selected-address').find('div.address-info')
					.find('input[type=hidden].e-code').val();
		window.location = url + addressCodeParam;
	});
	
	/*! Selects delivery method */
	bindDeliveryMethod_onChange();
	
	/*! Adds a new billing address */
	$('button[type=button].buttonBillingAddressFormSendForm').click(function() {
		$.ajax({
			async : true,
			url : contextPath + '/checkout/single/add-billing-address',
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
	
	/* Applies or removes voucher */
	 $('div#voucherModal').find('.applyVC').click(function() {
		 var url = $(this).siblings('input[type=hidden][name=applyVcUrl]').val();
		 var vrc = $('input[type=text][name=voucher]').val();
		 vrc = vrc === '' ? $('input[type=hidden][name=vcCode]').val() : vrc;
		 if(vrc) {
			 $.ajax({
				 async : true,
					url : url +  vrc,
					type : 'POST',
					dataType : 'json',
					data : { voucherCode : vrc },
					success : function(data) {
						if(data) {
							var htmlSubTotals = data.subtotalsAmount.productsAmount;
							htmlSubTotals += data.subtotalsAmount.freightAmount;
							htmlSubTotals += data.subtotalsAmount.discountAmount;
							var htmlDeliveryMethodSelector = data.deliveryMethodSelector;
							$('div#CreditCardInstalment').html(data.instalmentCreditCard);
							$('div#CreditCardInstalment').find("#numero-parcelas").prop("name", 'instalment');							
							$('div#checkoutSubTotals').html(htmlSubTotals);
							$('div#checkoutPaymentTotals').html(data.paymentTotalAmount);
							$('section#shipping-methods').html(htmlDeliveryMethodSelector);							
							if(data.success === 'true') {
								if(data.command === 'applyVoucher') {
									$('input[type=hidden][name=vcCode]').val(vrc);
									$('div.vale-credito-not-applied').hide();
									$('div.vale-credito-applied').show();
									setChosenPaymentModes();
									togglePaymentSelection(data.isVoucherAmountEqualsOrderAmount)
								} else {
									$('input[type=hidden][name=vcCode]').val('');
									$('div.vale-credito-not-applied').show();
									$('div.vale-credito-applied').hide();
									setChosenPaymentModes();
									togglePaymentSelection(data.isVoucherAmountEqualsOrderAmount);
								}
							}
							showToaster(data.message);
						}
					},
					complete : function() {
						//TODO isso é temporario, até termos o ajax funcionando
						location.reload();
						bindDeliveryMethod_onChange();
					}
			 });
		 }
	});
	
	 
	/*! Selects credit card brand */
	$("#payment-credit-card input#cardNumber").on("keyup, blur",function() {
		var cardFlags = $("#payment-credit-card .card-flags ul li");
		cardFlags.addClass("disabled");
		var cc = $(this).val();
		var flag = getCreditCardLabel(cc);
		var liFlag = "li."+flag;
		if($(this).val().length > 0) {
			if(flag !== "") {
				$('#payment-credit-card input[type=radio][value='+flag+']')
						.prop('checked', true);
				cardFlags.parent().find(liFlag).removeClass("disabled");
				setChosenPaymentModes("Cartão " + flag.charAt(0).toUpperCase() + flag.slice(1));
			} else {
				cardFlags.addClass("disabled");
				setChosenPaymentModes()
			}
		} else {
			cardFlags.addClass("disabled");
			setChosenPaymentModes();
		}
	});
	
	
	/*! Changes order details payment mode */
	$('form#paymentDetailsForm').find('input[name=paymentMode], input[name=isVoucherSelected]').click(function() {
		setChosenPaymentModes();
	});
	
	
	/*! Sends payment form */
	$('.cart150529 .text-right').find('button[type=button].confirm-purchase').click(function() {
		if(!validateCCForm()) {
			allowClickPaymentButton();
			return false;
		}
		var formPayment = $('form#paymentDetailsForm');
		var formBillingAddress = $('');
		$('form#heringBillingAddressForm').find('input,select').each(function(){
			var input = $(this).clone();
			$(formPayment).find('input[name="billingAddress.'
					+ $(input).prop('name') + '"]').remove();
			$(input).prop('name', 'billingAddress.' + $(input).prop('name'));
			$(input).prop('type', 'hidden');
			$(input).hide();
			// MacGuyver to copy <select> elements selected option
			$(input).val($(this).val());
			$(input).appendTo(formPayment);
		});
		formPayment.submit();
	});
	
	
	/*! exibir lista de produtos quando o dt de itens for clicado */
	$('#general-info dt.items-title').click(function(e) {
		$('#general-info dd.items-content').slideToggle("medium");
		$(this).toggleClass('opened');
	})

	/*! leia-mais-docs */
	$('#docs').after('<a href="#" id="docs-leia-mais">[...]veja mais</a>');
	$('#docs-leia-mais').click(function(e){
		e.preventDefault();
		if(!$(this).hasClass('opened')) {
			$('#docs').css({'max-height': '100%'});
			$(this).addClass('opened');
			$(this).text('Fechar')
		} else {
			$('#docs').css({'max-height': 244});
			$(this).removeClass('opened');
			$(this).text('[...]veja mais')
		}
	});
	
	$('#cardNumber').focus(function(){
		$('#payment-credit-card input[type=radio][value=CreditCard]').prop('checked',true);
	});
	
});


/*! Selects delivery method */
function bindDeliveryMethod_onChange() {
	var contextPath = $('input[type=hidden].contextPath').val();
	$('section#shipping-methods').find('input[type=radio][name=delivery_method]').on('change',
	function() {
		var selectedDeliveryAddressCode = $(this).val();
		var url = contextPath + '/checkout/single/select-delivery-method/';
		if(selectedDeliveryAddressCode) {
			$.ajax({
				async : true,
				url : url + selectedDeliveryAddressCode,
				type : 'POST',
				dataType : 'json',
				data : { selectedDeliveryMethod : selectedDeliveryAddressCode },
				success : function(data) {
					if(data && data.success === 'true') {
						var htmlSubTotals = data.subtotalsAmount.productsAmount;
						htmlSubTotals += data.subtotalsAmount.freightAmount;
						htmlSubTotals += data.subtotalsAmount.discountAmount;
						var htmlDeliveryMethodSelector = data.deliveryMethodSelector;
						$('div#checkoutSubTotals').html(htmlSubTotals);
						$('div#checkoutPaymentTotals').html(data.paymentTotalAmount);
						$('section#shipping-methods').html(htmlDeliveryMethodSelector);
					}
					showToaster(data.message);
				}, complete : function() {
					bindDeliveryMethod_onChange();
				}
			});
		}
	});
}

function validateCCForm() {
	var selectedMode = $('form#paymentDetailsForm').find('input[name=paymentMode]:checked').val();
	
	var success = true;
	if('CreditCard' == selectedMode) {
		var errorStyle = 'border: 1px solid red;'
		var ccNumber = $.trim($('input[name=cardNumber]').val());
		var ccHolder = $.trim($('input[name=nameOnCard]').val());
		var ccEMonth = $.trim($('select[name=expiryMonth]').val());
		var ccEYear = $.trim($('select[name=expiryYear]').val());
		var ccCvc = $.trim($('input[name=cv2Number]').val());
		
		if(ccNumber.length == 0) {
			$('input[name=cardNumber]').attr('style', errorStyle);
			success = false;
		}
		
		if(ccHolder.length == 0) {
			$('input[name=nameOnCard]').attr('style', errorStyle);
			success = false;
		}
		
		if(ccEMonth.length == 0 || ccEMonth == 0) {
			$('select[name=expiryMonth]').attr('style', errorStyle);
			success = false;
		}
		
		if(ccEYear.length == 0 || ccEYear == 0) {
			$('select[name=expiryYear]').attr('style', errorStyle);
			success = false;
		}
		
		if(ccCvc.length == 0) {
			$('input[name=cv2Number]').attr('style', errorStyle);
			success = false;
		}
	}
	return success;
}


function resizeColumns() {
	var columns = $('#main-wrapper.co .checkout-column');
	var higherHeight = 0;
	if($(window).width() >= 1199) { 
		columns.each(function() {
			if($(this).outerHeight() > higherHeight) {
				higherHeight = $(this).outerHeight();
			}
		});
		columns.css("min-height", higherHeight);
	} else {
		columns.css("min-height", 0);
	}
}


function getCreditCardLabel(cardNumber){
	var regexElo = /^(636368|438935|504175|451416|636297)(?:[0-9]{10})?|^(5066|5067|4576|4011)(?:[0-9]{12})?/;
	var regexHiper = /^(38|60)(([0-9]{13})|([0-9]{16})|([0-9]{19}))?/;
	var regexVisa = /^4([0-9]{12})?(?:[0-9]{3})?/;
	var regexMaster = /^5([1-5])?([0-9]{14})?/;
	var regexAmex = /^3[47]([0-9]{13})?/;
	var regexDiners = /^3(?:0[0-5]|[68][0-9])([0-9]{11})?/;
	var regexHering = /^432032(?:[0-9]{10})?/;

	if(regexHering.test(cardNumber)){ return 'hering'; }
	if(regexElo.test(cardNumber)){ return 'elo'; } 
	if(regexHiper.test(cardNumber)){ return 'hipercard'; } 
	if(regexVisa.test(cardNumber)){ return 'visa'; } 
	if(regexMaster.test(cardNumber)){ return 'master'; } 
	if(regexAmex.test(cardNumber)){ return 'amex'; } 
	if(regexDiners.test(cardNumber)){ return 'diners'; } 
	
	return '';
}

function changeOrderDetailsPaymentMethod(paymentMethod) {
	$("section#general-info").find("dl.payment-info").find("dd.method")
			.text(paymentMethod); 
}

function setChosenPaymentModes(specificCard) {
	var PaymentModes = new Array();
	paymentModes = $('form#paymentDetailsForm').find(
			'input[name=paymentMode]:checked,input[name=isVoucherSelected]:checked');
	var selectedPaymentModes = "";
	if(!specificCard) {
		$(paymentModes).each(function() {
			selectedPaymentModes += $(this).prop('id').charAt(0).toUpperCase()
				+ $(this).prop('id').slice(1);
			selectedPaymentModes += paymentModes.last().prop('id') === $(this).prop('id')
					? " " : " / ";
		});
	} else {
		selectedPaymentModes += specificCard;
	}
	selectedPaymentModes += $('div.vale-credito-applied').css('display') == 'none' ? "" :
		" / Vale Crédito";
	changeOrderDetailsPaymentMethod(selectedPaymentModes);
}

function allowClickPaymentButton()
{
	$(".waiting-order-confirm").hide();
	$(".btn-checkout").show();
}

function blockTwoClickPaymentButton()
{
	$(".btn-checkout").hide();
	$(".waiting-order-confirm").show();
}

function togglePaymentSelection(block) {
	// Just accept it and mind your own business...
	if(block !== "false") {
		$('form#paymentDetailsForm').find('section#payment-credit-card input')
				.prop('disabled', true);
		$('form#paymentDetailsForm').find('section#payment-credit-card select')
				.prop('disabled', true);
		$('form#paymentDetailsForm').find('section#payment-credit-card input[type=radio]')
				.prop('checked', false);
		$('form#paymentDetailsForm').find('section#payment-credit-card input[type=checkbox]')
				.prop('checked', false);
		$('form#paymentDetailsForm').find('section#payment-billet input[type=radio]')
				.prop('checked', false);
	} else {
		$('form#paymentDetailsForm').find('section#payment-credit-card input')
				.prop('disabled', false);
		$('form#paymentDetailsForm').find('section#payment-credit-card select')
				.prop('disabled', false);
	}	
}