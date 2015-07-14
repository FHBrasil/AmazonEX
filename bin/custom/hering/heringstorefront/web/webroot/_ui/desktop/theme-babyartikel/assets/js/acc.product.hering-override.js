// replace/override function
ACC.product.bindToAddToCartForm = function() {
	var addToCartForm = $('.addToCartForm');
	addToCartForm.ajaxForm({
		success: ACC.product.displayAddToCartPopup
	});
};

// uses class instead of id
ACC.product.enableAddToCartButton = function() {
    $('.addToCartButton').removeAttr("disabled");
};

// updates minicart without popup
ACC.product.displayAddToCartPopup = function(cartResult, statusText, xhr, formElement) {
	
	if (typeof ACC.minicart.refreshMiniCartCount == 'function') {
		ACC.minicart.getMiniCartData("");
		ACC.minicart.refreshMiniCartCount();
	}
	
	var error = $(cartResult.addToCartLayer).find('.cart_popup_error_msg');
	if(error.length && error.text() != '')
		showToaster(error.text());
	
	var legend = $(cartResult.addToCartLayer).find('.legend');
	if((legend.length && legend.text() != '') && !(error.length && error.text() != ''))
		showToaster(legend.text());
	
	var confirmation = $(cartResult.addToCartLayer).find('.confirmation');
	if((confirmation.length && confirmation.text() != '') && !(error.length && error.text() != ''))
	{
		if(confirmation.text() == "ok")
		{
			$('#cartModal').modal('show');
		}
	}
	
	
		
	
	if (typeof ACC.minicart.refreshMiniCartCount == 'function') {
		ACC.minicart.refreshMiniCartCount();
	}
	var productCode = $('[name=productCodePost]', formElement).val();
	var quantityField = $('[name=qty]', formElement).val();
	var quantity = 1;
	if (quantityField != undefined) {
		quantity = quantityField;
	}
	ACC.track.trackAddToCart(productCode, quantity, cartResult.cartData);
	
	setTimeout(function(){
		$(".miniCartPopup").show();
		$(".miniCartPopup div").show();
	}, 1000);

	
	
	setTimeout(function(){ $(".miniCartPopup div").hide();	}, 7000);
};