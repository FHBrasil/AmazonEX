$(document).ready(function() {
	
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

    // validação de formulários (em script.js)
    hering.form.validate();

	// define a maior altura para todas as colunas
	resizeColumns();
	$(window).resize(function() { resizeColumns(); });

	// abre e fecha sections em responsivo
	$('.columns-wrapper section header h2').click(function() {
		if($(window).width() <= 1199) {
			$(this).parents('.columns-wrapper').find('.checkout-column').each(function() {
                $(this).find('.section-container').slideUp('slow');
            });
			$(this).parents('section').find('.section-container').slideDown();
		}
	});

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

    $('.btn-editar').click(function(e){ return;
        e.preventDefault();
        var selectedAddress = $('section#selected-address div.address-info');
        $('#new-address form')[0].reset();
        $('#new-address #new-addr-addressId').val($(selectedAddress).find('.e-code').text());
        $('#new-address #new-addr-cep').val($(selectedAddress).find('.e-cep').text());
        $('#new-address #new-addr-tipo').val($(selectedAddress).find('.e-tipo').text());
        $('#new-address #new-addr-addr').val($(selectedAddress).find('.e-addr').text());
        $('#new-address #new-addr-numero').val($(selectedAddress).find('.e-numero').text());
        $('#new-address #new-addr-bairro').val($(selectedAddress).find('.e-bairro').text());
        $('#new-address #new-addr-complemento').val($(selectedAddress).find('.e-complemento').text());
        $('#new-address #new-addr-estado').val($(selectedAddress).find('.e-estado').text());
        $('#new-address #new-addr-cidade').val($(selectedAddress).find('.e-cidade').text());
        $('#new-address').slideDown('slow');
        // mmuda o nome do botão de editar
        // $('#new-address .btn-addr-cadastrar').html('Cadastrar e utilizar este endereço');
    });

    $('section#selected-address').find('div.address-info').find('div.btn-group')
    .find('a.btn-editar').click(function() {
    	var url = contextPath + '/checkout/multi/single-step-checkout/'
    			+ 'edit-delivery-address?';
    	var addressCodeParam = 'addressCode=' 
    			+ $('section#selected-address').find('div.address-info')
    				.find('input[type=hidden].e-code').val();
    	window.location = url + addressCodeParam;
    });
    
    
    $('section#selected-address').find('div.address-info').find('div.btn-group')
    .find('a.btn-excluir').click(function() {
    	var url = contextPath + '/checkout/multi/single-step-checkout/'
    			+ 'remove-address?';
    	var addressCodeParam = 'addressCode=' 
    			+ $('section#selected-address').find('div.address-info')
    				.find('input[type=hidden].e-code').val();
    	window.location = url + addressCodeParam;
    });
    
    $('input[type=text][name=cartao_presente]').blur(function() {
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
    
    $("#payment-credit-card input#cardNumber").change(function() {
    	var creditCard = $(this).val();
    	var selectedCreditCardFlag = getCreditCardLabel(creditCard);
    	if(selectedCreditCardFlag) {
    		$('input[type=radio][value='+selectedCreditCardFlag+']').prop('checked', 'checked');
    		
    		$('.card-flags ul li').each(function(){
    			var classe = $(this).attr("class");
    			if( !(classe.indexOf("disabled")!=-1) )	
    				$("."+classe).attr("class",classe + " disabled");
    		});
    		
    		$("li." + selectedCreditCardFlag).attr("class",selectedCreditCardFlag);
    	}
    });
    
    
    $('section#co-right').find('button[type=button].confirmPurchase')
    .click(function() {
		var formPayment = $('form#paymentDetailsForm').serialize();
		var formBilling = $('form#heringBillingAddressForm').serializeArray();
		for(i in formBilling) {
		    formPayment += '&billingAddress.' + formBilling[i].name + '=' 
		    + formBilling[i].value;
		}
		var url = contextPath + '/checkout/multi/single-step-checkout/placeOrder';
		window.location = (url + '?' + formPayment);
	});
    
    
    //exibir lista de produtos quando o dt de itens for clicado
    $('#general-info dt.items-title').click(function(e) {
        $('#general-info dd.items-content').slideToggle("medium");
        $(this).toggleClass('opened');
    })

    //leia-mais-docs
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
});

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
    var regexVisa = /^4([0-9]{12})?(?:[0-9]{3})?/;
    var regexMaster = /^5([1-5])?([0-9]{14})?/;
    var regexAmex = /^3[47]([0-9]{13})?/;
    var regexDiners = /^3(?:0[0-5]|[68][0-9])([0-9]{11})?/;
    if(regexVisa.test(cardNumber)) {
    	return 'visa';
	} 
    if(regexMaster.test(cardNumber)) {
		return 'master';
	} 
    if(regexAmex.test(cardNumber)) {
    	return 'amex';
	} 
    if(regexDiners.test(cardNumber)) {
    	return 'diners';
	} 
    return '';
}