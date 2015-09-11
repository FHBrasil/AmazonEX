$(document).ready(function() {
	
	var contextPath = $('input[type=hidden].contextPath').val();
	
	$('.addressbook150706 .modal-footer').find('button.btn-primary').click(function(){			
		var typeAddress = $('input:hidden[name=deliveryOrBilling]').val();
		var cod = $('input:radio[name=chooseDeliveryAddress' + typeAddress + ']:checked').val();
		var url = contextPath + '/my-account/select-' + typeAddress + '-address/' + cod;
		$('form#selectAddress').attr('action', url).submit();
	});
	
	$('.pull-right .btn-editar, .addressbook150706 .editAddress').click(function(){
		if($(this).hasClass('billing'))
		{
			$('input:hidden[name=deliveryOrBilling]').attr("value", 'billing');
			$('input:hidden[name=doDeliveryDoBilling]').attr("value", 'billing');
		}
		else
		{
			$('input:hidden[name=deliveryOrBilling]').attr("value", 'delivery');
			$('input:hidden[name=doDeliveryDoBilling]').attr("value", 'delivery');
		}
	});
	
	var editAddress = $('input:hidden[name=isEditAddress]').val();
	if(editAddress === "true"){
		$('#editAddressModal').modal('show');
		$('form.addEditDeliveryAddressForm .modal-footer .btn-default, .addressbook150706 .modal-footer .btn-default').click(function(){
			window.location = contextPath + "/my-account";
		});
	}
	
	$('.addressbook150706 .modal-footer .btn-default').click(function(){
		window.location = contextPath + "/my-account";
	});
	
	hering.form.validateAll();
	
    // fancybox
    $(".fancybox.alterar-foto").fancybox({
        maxWidth: 400
    });

    // validação de formulários (em script.js)
    hering.form.validateAll();

    // Ao digitar o numero máximo de caracteres no input, pula para o próximo campo
    jumptToNextInput("#acc-address-form .cep, #search-bar .pesquisa-data, #acc-dados-form .form-data");
    
    // define a maior altura para todas as caixas da listagem de endereços
    resizeAddrBoxes();
    $(window).resize(function() { resizeAddrBoxes(); });

    
    $(".has-sub-item .tracking").click(function() {
    	trackingClick = true;
    });
    
    $(".has-sub-item").click(function(e) {
    	
    	if(!trackingClick) {
    		e.preventDefault();
    		subItemToggleBtn = $(this).find(".sub-item-toggle");
    		subItemId = $(this).attr("data-sub-item");
    		$(this).parents('table') // sobe até a tag table
    		.find(".sub-item-"+subItemId+" div.details")
    		.slideToggle(500);
    		subItemToggleBtn.toggleClass("active");
    	}

    	trackingClick = false;
    });
});

$(window).load(function() {
	var editPackstation = $('input:hidden[name=editPackstation]').val();
	if(editPackstation === "false"){
		$('#addresstype-address').click();
		$('#addresstype-packstation').attr('disabled', true);
		$('.closeNewAddress').removeAttr('data-target');
	}
	else if(editPackstation === "true"){		
			$('.addresstype-packstation').click();		
			$('#addresstype-address').attr('disabled', true);
			$('.closeNewAddress').removeAttr('data-target');
	}
	else
		$('#addresstype-address').click();
});

trackingClick = false;    
    
function resizeAddrBoxes() {
    var boxes = $('.page.account-edit-addresses #account-addresses ul li');
    boxes.css("min-height", 1);
    var higherHeight = 0;
    if($(window).width() >= 680) { 
        boxes.each(function() {
            if($(this).outerHeight() > higherHeight) {
                higherHeight = $(this).outerHeight();
            }
        });
        boxes.css("min-height", higherHeight);
    } else {
        boxes.css("min-height", 1);
    }
}