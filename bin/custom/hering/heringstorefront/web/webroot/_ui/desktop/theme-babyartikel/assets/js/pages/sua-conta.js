$(document).ready(function() {
	
	var contextPath = $('input[type=hidden].contextPath').val();
	
	$('form#selectAddress').find('button.btn-primary').click(function(){		
		var cod = $('input:radio[name=chooseDeliveryAddress]:checked').val();
		var typeAddress = $('input:hidden[name=deliveryOrBilling]').val();
		var url = contextPath + '/my-account/select-' + typeAddress + '-address/' + cod;
		$('form#selectAddress').attr('action', url).submit();
	});
	
	$('.pull-right .btn-editar').click(function(){
		if($(this).hasClass('billing'))
			$('input:hidden[name=deliveryOrBilling]').attr("value", 'billing');
		else
			$('input:hidden[name=deliveryOrBilling]').attr("value", 'delivery');
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