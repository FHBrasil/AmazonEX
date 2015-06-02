$(function() {
	if($('[name=paymentMethodSelection]').length > 0){
	    paymentMethod();
        $('[name=paymentMethodSelection]').change(function(){
        	paymentMethod();
        });
        $('#payPalSilentOrderSubmitButton').click(function(){
        	window.location = $('#expressCheckoutUrl').text();
        });
	}

});

function paymentMethod() {
	if($('input[name=paymentMethodSelection]:radio:checked').val() == ''
		|| $('input[name=paymentMethodSelection]:radio:checked').val() == undefined) {
		$('.payment_details_left_col').hide();
		$('#payPalSilentOrderSubmitButton').parent().show();
	} else if($('input[name=paymentMethodSelection]:radio:checked').val() == 'paypal') {
		$('.payment_details_left_col').hide();
		$('#payPalSilentOrderSubmitButton').parent().show();
	} else {
		$('.payment_details_left_col').show();
		$('#payPalSilentOrderSubmitButton').parent().hide();
	}
}
