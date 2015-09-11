ACC.product = {
    initQuickviewLightbox: function() {
        this.enableAddToCartButton();
        this.bindToAddToCartForm();
    },
    enableAddToCartButton: function() {
        $('#addToCartButton').removeAttr("disabled");
    },
    enableStorePickupButton: function() {
        $('.pickupInStoreButton').removeAttr("disabled");
    },
    bindToAddToCartForm: function() {
        var addToCartForm = $('.add_to_cart_form');
        addToCartForm.ajaxForm({
            success: ACC.product.displayAddToCartPopup
        });
    },
    bindToAddToCartStorePickUpForm: function() {
        var addToCartStorePickUpForm = $('#pickup_store_results .add_to_cart_storepickup_form');
        addToCartStorePickUpForm.ajaxForm({
            success: ACC.product.displayAddToCartPopup
        });
    },
    displayAddToCartPopup: function(cartResult, statusText, xhr, formElement) {
        $('#addToCartLayer').remove();
        if (typeof ACC.minicart.refreshMiniCartCount == 'function') {
            ACC.minicart.refreshMiniCartCount();
        }
        $("#header").append(cartResult.addToCartLayer);
        $('#addToCartLayer').fadeIn(function() {
            $.colorbox.close();
            if (typeof timeoutId != 'undefined') {
                clearTimeout(timeoutId);
            }
            timeoutId = setTimeout(function() {
                $('#addToCartLayer').fadeOut(function() {
                    $('#addToCartLayer').remove();
                });
            }, 5000);
        });
        var productCode = $('[name=productCodePost]', formElement).val();
        var quantityField = $('[name=qty]', formElement).val();
        var quantity = 1;
        if (quantityField != undefined) {
            quantity = quantityField;
        }
        ACC.track.trackAddToCart(productCode, quantity, cartResult.cartData);
    },   
    
    bindReview: function() {
        $(document).on("click",  '.submitReviewHandler', function(e) {
            e.preventDefault();
            
            var headline = $('#review\\.headline').val();
        	var comment = $('#review\\.comment').val();
        	var rating = $('.star.active').length;
        	var alias = $('#review\\.alias').val();
        	var pCode = $('input[name=productCodePost]').val();
        	
        	$.ajax({
    	    	url : ACC.config.contextPath + '/p/' + pCode + '/review',
    	    	dataType : 'json',
    			type : 'GET',
    	    	data: {
    	    		headline: headline,
    	    		comment: comment,
    	    		rating: rating,
    	    		alias: alias
    	    	}
    	    })
    	    .done(function(json) {
    	    	var mJson = jQuery.parseJSON(json);
    	    	$('.feedback').html(mJson.msg);
    	    });
        });
    }
   
};
$(document).ready(function() {
    with(ACC.product) {
        bindToAddToCartForm();
        bindToAddToCartStorePickUpForm();
        enableAddToCartButton();
        enableStorePickupButton();        
        bindReview();
    }
});