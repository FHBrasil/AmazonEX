$(function() {
	paymentMethod();
	$('[name=paymentMethodSelection]').change(function() {
		paymentMethod();
	});
	$('#whatIsPayPalLink>a').live('click', function(){
        newwindow=window.open($(this).attr('href'),'','height=768,width=1280,menubar=no,status=no');
        if (window.focus) {newwindow.focus()}
        return false;
    });
});

function paymentMethod() {
	if ($('input[name=paymentMethodSelection]:radio:checked').val() == ''
			|| $('input[name=paymentMethodSelection]:radio:checked').val() == undefined) {
		$('.payWithCardSection').hide();
		$('#headlineDiv').removeClass('headline');
		$('#creditCardLabelId').addClass('headline');
	} else if ($('input[name=paymentMethodSelection]:radio:checked').val() == 'paypal') {
		$('.payWithCardSection').hide();
		$('#silentOrderSubmitButton').removeClass('submit_silentOrderPostForm');
		$('#headlineDiv').removeClass('headline');
		$('#creditCardLabelId').addClass('headline');
		$('#silentOrderSubmitButton').addClass('submit_silentOrderPostForm_paypal');
		$('.submit_silentOrderPostForm_paypal').click(function() {
			window.location = $('#expressCheckoutUrl').text();
		});
	} else {
		$('.payWithCardSection').show();
		$('#silentOrderSubmitButton').addClass('submit_silentOrderPostForm');
		$('#headlineDiv').addClass('headline');
		$('#creditCardLabelId').removeClass('headline');
		$('.submit_silentOrderPostForm_paypal').unbind('click');
		$('#silentOrderSubmitButton').removeClass('submit_silentOrderPostForm_paypal');
	}
}
